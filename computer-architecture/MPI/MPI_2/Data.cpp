#include "Data.h"
#include <mpi.h>

namespace MPI
{
	void Send(const int* data, int length, int destinationRank, int tag)
	{
		MPI_Send(data, length, MPI_INT, destinationRank, tag, MPI_COMM_WORLD);
	}

	void Receive(int* data, int length, int sourceRank, int tag)
	{
		MPI_Recv(data, length, MPI_INT, sourceRank, tag, MPI_COMM_WORLD, MPI_STATUS_IGNORE);
	}

	int* ReceiveIntegerArray(int length, int sourceRank, int tag)
	{
		int* data = new int[length];
		Receive(data, length, sourceRank, tag);
		return data;
	}

	void Broadcast(int* data, int length, int rootRank)
	{
		MPI_Bcast(data, length, MPI_INT, rootRank, MPI_COMM_WORLD);
	}
}