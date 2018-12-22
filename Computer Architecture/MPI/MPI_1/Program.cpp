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
		fin.open("task1_input.txt");
		fout.open("task1_output.txt");

		auto dataLength = 0;
		auto sliceSize = 0;
		
		if (process.isMaster())
		{
			dataLength = GetLength(fin);
			if (!dataLength)
				return;
		}

		MPI_Bcast(&dataLength, 1, MPI_INT, MPI::MasterRank, MPI_COMM_WORLD);
		sliceSize = dataLength / process.ProcessCount();

		int* firstVector = new int[dataLength];
		int* secondVector = new int[dataLength];
		int* slices = new int[process.ProcessCount()];
		int* nextSlices = new int[process.ProcessCount()];

		if (process.isMaster())
		{
			Read(firstVector, secondVector, dataLength, fin);

			for (int i = 0; i < process.ProcessCount() - 1; i++)
			{
				slices[i] = sliceSize;
				nextSlices[i] = i * sliceSize;
			}

			slices[process.ProcessCount() - 1] = sliceSize + dataLength % process.ProcessCount();
			nextSlices[process.ProcessCount() - 1] = (process.ProcessCount() - 1) * sliceSize;
		}

		if (process.Rank() == process.ProcessCount() - 1)
			sliceSize = dataLength / process.ProcessCount() + dataLength % process.ProcessCount();
		int* firstBuffer = new int[sliceSize];
		int* secondBuffer = new int[sliceSize];
		
		MPI_Scatterv(
			firstVector,
			slices,
			nextSlices,
			MPI_INT,
			firstBuffer,
			sliceSize,
			MPI_INT,
			MPI::MasterRank,
			MPI_COMM_WORLD);

		MPI_Scatterv(
			secondVector,
			slices,
			nextSlices,
			MPI_INT,
			secondBuffer,
			sliceSize,
			MPI_INT,
			MPI::MasterRank,
			MPI_COMM_WORLD);

		MultSum(firstBuffer, secondBuffer, sliceSize);

		MPI_Gatherv(
			firstBuffer,
			sliceSize,
			MPI_INT,
			firstVector,
			slices,
			nextSlices,
			MPI_INT,
			MPI::MasterRank,
			MPI_COMM_WORLD);

		if (process.isMaster())
		{
			fout << Sum(firstVector, dataLength);
		}
		
		delete[] firstVector;
		delete[] secondVector;
		delete[] firstBuffer;
		delete[] secondBuffer;
		
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

	static void Read(int* firstVector, int* secondVector, int dataLength, std::ifstream& fin)
	{
		for (int i = 0; i < dataLength; i++)
			fin >> firstVector[i];
		for (int i = 0; i < dataLength; i++)
			fin >> secondVector[i];
		fin.close();
	}

	static void MultSum(int* firstBuffer, int* secondBuffer, int sliceSize)
	{
		for (int i = 0; i < sliceSize; i++)
			firstBuffer[i] = firstBuffer[i] * secondBuffer[i];
	}

	static int Sum(int* firstVector, int dataLength)
	{
		int result = 0;
		for (int i = 0; i < dataLength; i++)
			result += firstVector[i];
		return result;
	}
};

void main()
{
	Program::Main();
}