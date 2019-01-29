__kernel void TestKernel(
	__global const char *sData,
	__global char *sResult,
	int stringLength)
{
	char symbol = 0;
	uchar one = 1;
	int index = get_global_id(0);

	if (index >= stringLength)
		return;

	for (int byteIndex = 0; byteIndex < 8; ++byteIndex)
		symbol |= (sData[index * 8 + byteIndex] & one) << (8 - byteIndex);

	sResult[index] = symbol;
}
