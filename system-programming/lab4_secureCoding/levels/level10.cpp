#include "jump_level.h"

int main(int argc, char **argv)
{
   
#define BUFSIZE 16
   
   int i = 0;   
   char buf[BUFSIZE];
   
   while (argv[0][i] && i < BUFSIZE)
     buf[i] = argv[0][i++];

   buf[i] = 0;
   
   if (i == 0)
     jump_level(11);   

   return 0;
}
