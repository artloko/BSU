#include "jump_level.h"

#define BUFSIZE 16

int main(int argc, char **argv)
{
   if (argc == 1)
     return -1;
   
   unsigned long a = 0,b = 0xffffffff;
   
   char buf[BUFSIZE];
   
   strcpy(buf,argv[1]);
      
   if (b == 0xffffff00 && !a)
		jump_level(8);
   else
		printf("maybe next time :)\n");
   
   return 0;

}
