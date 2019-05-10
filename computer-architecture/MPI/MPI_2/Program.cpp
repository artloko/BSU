#include <mpi.h>
#include <iostream>
#include <fstream>
#include <cctype>
#include "Process.h"
#include "Data.h"

class Program 
{

public:
	static void Main()
	{
		auto process = MPI::Process();
		std::ifstream(fin);
		fin.open("task2_input.txt");

		int dataLength = 0;
		int sliceSize = 0;

		if (process.isMaster())
			dataLength = GetLength(fin);
		MPI_Bcast(&dataLength, 1, MPI_INT, MPI::MasterRank, MPI_COMM_WORLD);

		sliceSize = dataLength * (dataLength + 1) / process.ProcessCount();
		int dataSize = sliceSize / (dataLength + 1);
		int* data = new int[sliceSize];
		int* result = new int[dataLength];
		int* solution = new int[dataLength];

		if (process.isMaster())
			SendData(process, data, solution, dataLength, fin);
		else
			ReceiveData(data);
		MPI_Bcast(solution, dataLength, MPI_INT, MPI::MasterRank, MPI_COMM_WORLD);

		int* buffer = new int[dataSize];
		
		Count(buffer, data, solution, sliceSize, dataLength + 1);
		
		MPI_Gather(buffer, dataSize, MPI_INT, result, dataSize, MPI_INT, MPI::MasterRank, MPI_COMM_WORLD);
		
		if (process.isMaster())
		{
			std::ofstream(fout);
			fout.open("task2_output.txt");
			for (int i = 0; i < dataLength; i++)
				fout << result[i] << " ";
			fout << "\n";
			fout.close();
		}

		delete[] result;
		delete[] data;
		delete[] solution;
		delete[] buffer;
		fin.close();
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

	static void SendData(MPI::Process& process, int* firstData, int* firstSolution, int dataLength, std::ifstream& fin)
	{
		int* vectors = nullptr;
		int* solution = nullptr;
		
		int entrySize = dataLength * (dataLength + 1);
		int sliceSize = entrySize / process.ProcessCount();

		vectors = new int[entrySize];
		solution = new int[dataLength];
		Read(vectors, entrySize, fin);
		ReadSolution(solution, dataLength, fin);

		for (int i = 0; i < sliceSize; i++)
			firstData[i] = vectors[i];
		for (int i = 0; i < dataLength; i++)
			firstSolution[i] = solution[i];

		for (int i = 1; i < process.ProcessCount(); i++)
			MPI_Send(&vectors[i * sliceSize], sliceSize, MPI_INT, i, 0, MPI_COMM_WORLD);
		
		delete[] solution;
		delete[] vectors;
	}

	static void ReceiveData(int* data)
	{
		MPI_Status status;
		MPI_Probe(MPI::MasterRank, 0, MPI_COMM_WORLD, &status);
		int sliceSize;
		MPI_Get_count(&status, MPI_INT, &sliceSize);
		MPI_Recv(data, sliceSize, MPI_INT, MPI::MasterRank, 0, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
	}

	static void Read(int* vectors, int entrySize, std::ifstream& fin)
	{
		for (int i = 0; i < entrySize; i++)
			fin >> vectors[i];
	}

	static void ReadSolution(int* solution, int dataLength, std::ifstream& fin)
	{
		for (int i = 0; i < dataLength; i++)
			fin >> solution[i];
	}

	static void Count(int* buffer, int* data, int* solution, int sliceSize, int dataLength)
	{
		int sum = 0;
		int j = 0;
		for (int i = 0; i < sliceSize; i++)
		{
			if ((i + 1) % dataLength == 0)
			{
				buffer[(i + 1) / dataLength - 1] = data[i] - sum;
				j = 0;
				sum = 0;
			}
			else
			{
				sum += data[i] * solution[j];
				j++;
			}
		}
	}
};

void main()
{
	Program::Main();
}