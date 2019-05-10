#include "KernelEx.h"
#include <fstream>
#include <iostream>

using namespace std;
using namespace cl;

string KernelEx::LoadText(const string& path)
{
	auto sourceFile = ifstream(path.c_str());
	return string(istreambuf_iterator<char>(sourceFile), istreambuf_iterator<char>());
}

Kernel KernelEx::BuildFromFile(const Device& device, const Context& context, const string& kernelFile, const string& kernelName)
{
	auto kernelSource = LoadText(kernelFile);
	return BuildFromSource(device, context, kernelSource, kernelName);
}

Kernel KernelEx::BuildFromSource(const Device &device, const Context &context, const string &kernelCode,
	const string &kernelName)
{
	auto source = Program::Sources(1, make_pair(kernelCode.c_str(), kernelCode.length() + 1));
	cl_int error;
	auto program = Program(context, source, &error);
	auto contextDevices = vector <Device>{ device };

	try
	{
		program.build({ device });
	}
	catch (cl::Error &e)
	{
		if (e.err() == CL_BUILD_PROGRAM_FAILURE)
		{
			cl_build_status status = program.getBuildInfo <CL_PROGRAM_BUILD_STATUS>(device);
			std::string name = device.getInfo <CL_DEVICE_NAME>();
			std::string buildlog = program.getBuildInfo <CL_PROGRAM_BUILD_LOG>(device);
			std::cerr << "Build log for " << name << ":" << std::endl
				<< buildlog << std::endl;
		}
	}

	return Kernel(program, kernelName.c_str());
}
