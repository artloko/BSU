#include "jump_level.h"

int main (int argc, char **argv)
{
   unsigned int saved_eip;
   unsigned int saved_ebp;
   char buf[80];

   if (argc == 1)
     return -1;

   strcpy(buf,argv[1]);

   if (saved_eip == 0x41424344)
     jump_level(12);
   else
	 printf("maybe next time :)\n");     

   return 0;
}
   
    
   

