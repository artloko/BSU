

__kernel void TestKernel(
	__global const char* sString,
	__global const char* sData,
	__global  char* sResult,
	int stringLength)
{
	
	char symbol;
	uchar one = 1;
	int index = get_global_id(0);
	if (index >= stringLength)
		symbol = 0;
	else
		symbol = sString[index];

	for (int byteIndex = 0; byteIndex < 8; ++byteIndex)
	{
		char number = sData[index * 8 + byteIndex];
		number ^= (symbol >> (8 - byteIndex) ^ number) & one;
		sResult[index * 8 + byteIndex] = number;
	}
}