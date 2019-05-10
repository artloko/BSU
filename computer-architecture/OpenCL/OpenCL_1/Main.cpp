#include "OpenCL.h"
#include "DeviceEx.h"
#include <iostream>
#include <vector>
#include "KernelEx.h"
#include <fstream>

using namespace std;
using namespace cl;

class Main
{
public:
	static void Run(int argc, char* argv[])
	{
		try
		{
			auto device = DeviceEx::Find("NVIDIA");
			RunOnDevice(device, argc, argv);
		}
		catch (const exception &e)
		{
			cerr << e.what() << endl;
		}
	}

private:
	static void RunOnDevice(const Device& device, int argc, char* argv[])
	{
		if (argv[1][1] == 'e') 
		{
			if (argc != 5)
				return;

			ifstream input(argv[2], ios::binary | ios::in);
			ofstream output(argv[3], ios::binary | ios::out);

			if (!input.eof())
			{
				ofstream out(argv[2], ios::binary | ios::out);
				int n = 25, m = 2;
				char zeros[200];
				for (int i = 0; i < 200; i++)
					zeros[i] = 0;
				out.write((char*)&n, sizeof(n));
				out.write((char*)&m, sizeof(m));
				out.write(zeros, 200 * sizeof(char));
				out.close();
			}

			char* string = argv[4];
			int stringLength = 0;
			while (string[stringLength] != '\0')
				stringLength++;
			output.write((char*)&stringLength, sizeof(stringLength));
			int n, m;
			input.read((char *)&n, sizeof(n));
			input.read((char *)&m, sizeof(m));
			int dataLength = 4 * n * m;
			output.write((char*)&dataLength, sizeof(dataLength));

			if (stringLength > dataLength / 8)
			{
				cout << "Not enought data to crypt: \"";
				for (int i = 0; i < dataLength; i++)
					cout << string[i];
				cout << "\"\n";
				return;
			}
			char* data = new char[dataLength];
			char* result = new char[dataLength] {};
			input.read(data, dataLength * sizeof(char));

			auto context = DeviceEx::CreateContext(device);
			auto stringBuffer = CreateBuffer(context, string, stringLength, CL_MEM_READ_ONLY);
			auto dataBuffer = CreateBuffer(context, data, dataLength, CL_MEM_READ_ONLY);
			auto resultBuffer = CreateBuffer(context, result, dataLength, CL_MEM_READ_ONLY);

			auto kernelCode = LoadText("Encrypt.cl");
			auto kernel = KernelEx::BuildFromSource(device, context, kernelCode, "TestKernel");
			kernel.setArg(0, stringBuffer);
			kernel.setArg(1, dataBuffer);
			kernel.setArg(2, resultBuffer);
			kernel.setArg(3, stringLength);

			auto queue = CommandQueue(context, device);
			queue.enqueueNDRangeKernel(kernel, NullRange, NDRange(stringLength));
			queue.finish();
			ReadFromBuffer(queue, result, dataLength, resultBuffer);
			PrintToFile(output, result, dataLength);
			ReleaseMemory(data, result);
			input.close();
		}
		else if (argv[1][1] == 'd')
		{
			if (argc != 3)
				return;

			ifstream input(argv[2], ios::binary | ios::in);
			int stringLength = 0, dataLength = 0;
			input.read((char*)&stringLength, sizeof(stringLength));
			input.read((char*)&dataLength, sizeof(dataLength));
			char* string = new char[stringLength] {};
			char* data = new char[dataLength];
			input.read(data, dataLength * sizeof(char));

			auto context = DeviceEx::CreateContext(device);
			auto stringBuffer = CreateBuffer(context, string, stringLength, CL_MEM_READ_ONLY);
			auto dataBuffer = CreateBuffer(context, data, dataLength, CL_MEM_READ_ONLY);

			auto kernelCode = LoadText("Decrypt.cl");
			auto kernel = KernelEx::BuildFromSource(device, context, kernelCode, "TestKernel");
			kernel.setArg(0, dataBuffer);
			kernel.setArg(1, stringBuffer);
			kernel.setArg(2, stringLength);

			auto queue = CommandQueue(context, device);
			queue.enqueueNDRangeKernel(kernel, NullRange, NDRange(dataLength));
			queue.finish();
			ReadFromBuffer(queue, string, stringLength, stringBuffer);
			PrintData(string, stringLength);
			ReleaseMemory(data, string);
			input.close();
		}
		else
			cout << "No such command";
	}

	static Buffer CreateBuffer(const Context& context, char* array, int size, int accessFlag)
	{
		return Buffer(context, accessFlag | CL_MEM_COPY_HOST_PTR, size * sizeof(char), array);
	}

	static string LoadText(const string& path)
	{
		auto sourceFile = ifstream(path.c_str());
		return string(istreambuf_iterator<char>(sourceFile), istreambuf_iterator<char>());
	}

	static void ReadFromBuffer(const CommandQueue& queue, char* array, int size, const Buffer& buffer)
	{
		queue.enqueueReadBuffer(buffer, CL_TRUE, 0, size * sizeof(char), array);
	}

	static void PrintData(char* string, int stringLength)
	{
		for (auto i = 0; i < stringLength; ++i)
			cout << string[i];
		cout << "\n";
	}

	static void PrintToFile(std::ofstream& out, char* encryptedData, int dataLength)
	{
		for (int i = 0; i < dataLength; i++)
			out.write((char*)&encryptedData[i], sizeof(encryptedData[i]));
		out.close();
	}

	static void ReleaseMemory(char*& data, char*& result)
	{
		delete[] data;
		delete[] result;
	}
};

int main(int argc, char* argv[])
{
	Main::Run(argc, argv);
	system("pause");
}