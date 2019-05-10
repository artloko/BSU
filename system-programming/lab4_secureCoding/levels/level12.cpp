#include "jump_level.h"

void unreachable();

int main (int argc, char **argv)
{
	char buf[80];

	if (argc == 1)
		return -1;

	strcpy(buf,argv[1]);
  
	printf("0x%x\n",unreachable);
	
	return 0;
}

void unreachable()
{
	jump_level(13);
}

   
    
   

