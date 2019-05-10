#pragma once

namespace MPI
{
	void Send(const int* data, int length, int destinationRank, int tag);
	void Receive(int* data, int length, int sourceRank, int tag);
	int* ReceiveIntegerArray(int length, int sourceRank, int tag);

	void Broadcast(int* data, int length, int rootRank);
}
