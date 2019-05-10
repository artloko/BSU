#include "jump_level.h"


int main(int argc, char **argv)
{
	if (argc != 2)
		return -1;
   
	char c = strlen(argv[1]);
   
	if (c < 0)
		jump_level(2);
	else
		printf("maybe next time :)\n");

   return 0;
}
