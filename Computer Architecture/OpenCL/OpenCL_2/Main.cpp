#include "OpenCL.h"
#include "DeviceEx.h"
#include <iostream>
#include "KernelEx.h"
#include "Matrix.h"
#include "KernelCodeGenerator.hpp"
#include <iomanip>
#include <chrono>
#include <Windows.h>
#include <WinBase.h>

struct HighResClock
{
	typedef long long rep;
	typedef std::nano period;
	typedef std::chrono::duration <rep, period> duration;
	typedef std::chrono::time_point <HighResClock> time_point;
	static const bool is_steady = true;

	static time_point now();
};

namespace
{
	const long long g_Frequency = []() -> long long
	{
		LARGE_INTEGER frequency;
		QueryPerformanceFrequency(&frequency);
		return frequency.QuadPart;
	} ();
}

HighResClock::time_point HighResClock::now()
{
	LARGE_INTEGER count;
	QueryPerformanceCounter(&count);
	return time_point(duration(count.QuadPart * static_cast<rep>(period::den) / g_Frequency));
}

using namespace std;
using namespace cl;

class Main
{
public:
	static void Run()
	{
		try
		{
			auto device = DeviceEx::Find("NVIDIA");
			RunOnDevice(device);
		}
		catch (const exception &e)
		{
			cerr << e.what() << endl;
		}
	}

private:
	static void RunOnDevice(const Device &device)
	{
		srand(0);
		auto n = 257 * 2;
		auto m = 257 * 2;
		auto l = 257 * 2;
		auto m1 = CreateMatrix(n, m);
		auto m2T = CreateMatrix(l, m);

		auto context = DeviceEx::CreateContext(device);
		auto m1Buffer = CreateBuffer(context, m1, CL_MEM_READ_ONLY);
		auto m2TBuffer = CreateBuffer(context, m2T, CL_MEM_READ_ONLY);

		NonParallel(m1, m2T, 10);
		TestKernel(device, context, m1Buffer, m2TBuffer, n, m, l, KernelEx::LoadText("Kernels.cl"), "ScalarMultiply", 10);
		TestKernel(device, context, m1Buffer, m2TBuffer, n, m, l, KernelCodeGenerator::Generate(2), "VectorMultiply2", 10);
		TestKernel(device, context, m1Buffer, m2TBuffer, n, m, l, KernelCodeGenerator::Generate(3), "VectorMultiply3", 10);
		TestKernel(device, context, m1Buffer, m2TBuffer, n, m, l, KernelCodeGenerator::Generate(4), "VectorMultiply4", 10);
		TestKernel(device, context, m1Buffer, m2TBuffer, n, m, l, KernelCodeGenerator::Generate(8), "VectorMultiply8", 10);
		TestKernel(device, context, m1Buffer, m2TBuffer, n, m, l, KernelCodeGenerator::Generate(16), "VectorMultiply16", 10);

		m1.Dispose();
		m2T.Dispose();
	}

	static void NonParallel(Matrix first, Matrix second, int testsCount)
	{
		auto m3 = Matrix(first.Rows, second.Rows, new int[first.Rows * second.Rows]{});
		auto start = HighResClock::now();

		for (unsigned int index = 0; index < testsCount; ++index)
		{
			for (int firstRow = 0; firstRow < first.Rows; ++firstRow)
			{
				for (int secondRow = 0; secondRow < second.Rows; ++secondRow)
				{
					double sum = 0;
					int m1Base = firstRow * first.Columns;
					int m2Base = secondRow * first.Columns;
					for (int k = 0; k < first.Columns; ++k)
					{
						sum += first.Data[m1Base + k] * second.Data[m2Base + k];
					}
					m3.Data[firstRow * second.Rows + secondRow] = sum;
				}
			}
		}

		auto elapsed = HighResClock::now() - start;
		cout << "NonParallel time: " << fixed << setprecision(3) <<
			std::chrono::duration_cast <std::chrono::microseconds> (elapsed).count() * 1.0 / testsCount << " micros" << endl;

		m3.Dispose();
	}

	static double
		RunKernel(const Device &device, const Context &context, Buffer m1Buffer, Buffer m2TBuffer, int n, int m, int l,
			const string &kernelCode)
	{
		auto m3 = Matrix(n, l, new int[n * l]{});
		auto m3Buffer = CreateBuffer(context, m3, CL_MEM_READ_WRITE);

		auto kernel = KernelEx::BuildFromSource(device, context, kernelCode, "KernelMain");
		kernel.setArg(0, m1Buffer);
		kernel.setArg(1, m2TBuffer);
		kernel.setArg(2, m3Buffer);
		kernel.setArg(3, n);
		kernel.setArg(4, m);
		kernel.setArg(5, l);

		auto event = Event();
		auto queue = CommandQueue(context, device, CL_QUEUE_PROFILING_ENABLE);
		queue.enqueueNDRangeKernel(kernel, NullRange, NDRange(m3.Rows, m3.Columns), NullRange, nullptr, &event);
		event.wait();
		queue.finish();

		auto startTime = event.getProfilingInfo <CL_PROFILING_COMMAND_START>();
		auto endTime = event.getProfilingInfo <CL_PROFILING_COMMAND_END>();

		ReadFromBuffer(queue, m3, m3Buffer);
		//Print (m3);
		return (endTime - startTime) / 1000.0;
	}

	static void
		TestKernel(const Device &device, const Context &context, Buffer m1Buffer, Buffer m2TBuffer, int n, int m, int l,
			const string &kernelCode, const std::string &testName, int testsCount)
	{
		double total = 0.0;
		for (unsigned int index = 0; index < testsCount; ++index)
		{
			total += RunKernel(device, context, m1Buffer, m2TBuffer, n, m, l, kernelCode);
		}

		cout << testName << " time: " << fixed << setprecision(3) << (total / testsCount) << " micros" << endl;
	}

	static Matrix CreateMatrix(int n, int m)
	{
		auto size = n * m;
		auto matrix = new int[size];
		for (auto k = 0; k < size; ++k)
		{
			matrix[k] = -1 + rand() % 3;
		}
		return Matrix(n, m, matrix);
	}

	static void Print(Matrix matrix)
	{
		//return; //

		for (auto i = 0; i < matrix.Rows; ++i)
		{
			for (auto j = 0; j < matrix.Columns; ++j)
			{
				cout << matrix.Get(i, j) << " ";
			}
			cout << endl;
		}
		cout << endl;
	}

	static Buffer CreateBuffer(const Context &context, Matrix matrix, int accessFlag)
	{
		return Buffer(context, accessFlag | CL_MEM_COPY_HOST_PTR, matrix.Rows * matrix.Columns * sizeof(int),
			matrix.Data);
	}

	static void ReadFromBuffer(const CommandQueue &queue, Matrix matrix, const Buffer &buffer)
	{
		queue.enqueueReadBuffer(buffer, CL_TRUE, 0, matrix.Rows * matrix.Columns * sizeof(int), matrix.Data);
	}

	static void Print(int *counters, const int size)
	{
		for (auto i = 0; i < size; i += 3)
		{
			cout << "counters[" << i << "...] = " << counters[i] << ", " << counters[i + 1] << ", " << counters[i + 2]
				<< endl;
		}
	}
};

int main()
{
	Main::Run();
	system("pause");
}
