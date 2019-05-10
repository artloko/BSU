#pragma once

#include "OpenCL.h"
#include <string>

class KernelEx
{
public:
	static std::string LoadText(const std::string& path);
	static cl::Kernel BuildFromSource(const cl::Device& device, const cl::Context& context, const std::string& kernelCode, const std::string& kernelName);
	static cl::Kernel BuildFromFile(const cl::Device& device, const cl::Context& context, const std::string& kernelCode, const std::string& kernelName);
};