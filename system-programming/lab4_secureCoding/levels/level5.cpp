#include "jump_level.h"

int main(int argc, char **argv)
{
   char a = 5;
   char buf[16];
   
   gets(buf);
   
   if (a != 5)
		jump_level(6);
   else
		printf("maybe next time :)\n");
   
   return 0;
}
