#include "jump_level.h"

int main (int argc, char **argv)
{
   if (argc == 1)
     return -1;
   
   int a = strtol(argv[1],NULL,10);

   if (a < 0)
		return -1;

   unsigned int i = strtoul(argv[1],NULL,10);
   unsigned int s = i + 2;
   
   if (!s)
		jump_level(5);
   else
		printf("maybe next time :)\n");
   
   return 0;
}

   
