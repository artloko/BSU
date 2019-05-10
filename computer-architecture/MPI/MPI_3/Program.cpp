#include <mpi.h>
#include <iostream>
#include <fstream>
#include <cctype>
#include "Process.h"


class Program
{

public:
	static void Main()
	{
		auto process = MPI::Process();

		std::ifstream(fin);
		std::ofstream(fout);
		fin.open("task3_input.txt");
		fout.open("task3_output.txt");

		int* data = nullptr;
		int* result = nullptr;
		int* buffer = nullptr;
		auto dataLength = 0;
		auto sliceSize = 0;

		dataLength = GetLength(fin);
		if (!dataLength)
			return;
		sliceSize = dataLength / process.ProcessCount();

		if (process.isMaster())
		{
			data = new int[dataLength];
			GetData(data, dataLength, fin);
		}

		int* slices = new int[process.ProcessCount()];
		int* nextSlices = new int[process.ProcessCount()];
		for (int i = 0; i < process.ProcessCount() - 1; i++)
			slices[i] = dataLength / process.ProcessCount();

		for (int i = 0; i < process.ProcessCount(); i++)
			nextSlices[i] = i * sliceSize;

		slices[process.ProcessCount() - 1] = dataLength / process.ProcessCount() + dataLength % process.ProcessCount();
		if (process.Rank() == process.ProcessCount() - 1)
			sliceSize = dataLength / process.ProcessCount() + dataLength % process.ProcessCount();
		buffer = new int[sliceSize];
		result = new int[sliceSize];

		MPI_Scatterv(
			data,
			slices,
			nextSlices,
			MPI_INT,
			buffer,
			sliceSize,
			MPI_INT,
			MPI::MasterRank,
			MPI_COMM_WORLD);

		IsPrime(buffer, result, sliceSize);

		MPI_Gatherv(
			result,
			sliceSize,
			MPI_INT,
			data,
			slices,
			nextSlices,
			MPI_INT,
			MPI::MasterRank,
			MPI_COMM_WORLD);

		if (process.isMaster())
		{
			for (int i = 0; i < dataLength; i++)
				fout << data[i] << " ";
			fout << "\n";
		}		

		fin.close();
		fout.close();
	}

private:
	static int GetLength(std::ifstream& fin)
	{
		if (!fin.is_open() || fin.eof())
			return false;

		int dataLength = 0;
		fin >> dataLength;
		return dataLength;
	}

	static void GetData(int* data, int dataLength, std::ifstream& fin)
	{
		for (int i = 0; i < dataLength; i++)
			fin >> data[i];
	}

	static void IsPrime(int* buffer, int* result, int sliceSize) {
		int squareroot;

		for (int i = 0; i < sliceSize; i++)
		{
			if (buffer[i] > 10)
			{
				squareroot = (int)sqrt(buffer[i]);
				for (int j = 2; j <= squareroot; j++)
				{
					if (buffer[i] % j == 0)
					{
						result[i] = 0;
						break;
					}
				}
				if (result[i] != 0)
					result[i] = 1;
			}
			else
			{
				result[i] = (buffer[i] == 2 || buffer[i] == 3 || buffer[i] == 5 || buffer[i] == 7) ? 1 : 0;
			}
		}
	}
};

void main()
{
	Program::Main();
}