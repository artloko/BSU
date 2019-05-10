#include <iostream>
#include <string>

int strcmp(int maxLength, char* str1, char* str2)
{
	__asm
	{
		cld;
		mov ecx, maxLength;
		mov edi, str1;
		mov esi, str2;
		repe cmpsb;
		je equal_;
		mov eax, edi;
		sub eax, str1;
		jmp end_;
	equal_:
		mov eax, -1;
	end_:
	}
}

int main()
{
	char* str1 = new char[100];
	char* str2 = new char[100];
	std::cout << "Enter the first string: \n";
	std::cin >> str1;
	std::cout << "Enter the second string: \n";
	std::cin >> str2;

	int len1 = strlen(str1);
	int len2 = strlen(str2);

	int maxLength = (len1 < len2) ? len2 : len1;

	int firstDistinquish = strcmp(maxLength, str1, str2);

	if (firstDistinquish == -1)
		std::cout << "The Strings are equal\n";
	else
		std::cout << "The Strings aren't equal, first distinquish at " << firstDistinquish << " index\n";
	system("pause");
}