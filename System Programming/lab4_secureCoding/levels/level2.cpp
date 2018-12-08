#include "jump_level.h"

int main(int argc, char **argv)
{
   if (argc < 4)
     return -1;
   
   unsigned int ui = strtoul(argv[1],NULL,10);
   unsigned short us = strtoul(argv[2],NULL,10);
   unsigned char uc = strtoul(argv[3],NULL,10);
   
   if ((!ui || !us || !uc) || (uc == 1))
     return -1;
   
   if (!(ui+us*uc))
     jump_level(3);
   else
     printf("%u * %u + %u = %u\n",uc,us,ui,(ui+us*uc));

   return 0;
}



