#include "jump_level.h"

int main(int argc, char **argv)
{
	if (argc == 1)
		return -1;

	srand(time(NULL));

	unsigned int t = 0xaaaaaaaa,i = rand();
   
	printf(argv[1]);
	printf("\nGuess Random Numner (in hex):");
  
	scanf("%x",&t);
   
	if (t == i)
		jump_level(9);
	else
		printf("maybe next time :)\n");
   
	return 0;     
}
