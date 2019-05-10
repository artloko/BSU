#include "jump_level.h"

int main (int argc, char **argv)
{
   if (argc < 2)
		return -1;
   
   unsigned int i;
   
   if (atoi(argv[1]) > 200)
       return -1;
   
   i = atoi(argv[1]);
       
   
   if (i > 200)
		jump_level(4);
   else
		printf("maybe next time :)\n");

   return 0;
}
