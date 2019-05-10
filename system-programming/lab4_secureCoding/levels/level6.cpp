#include "jump_level.h"

int main(int argc,char **argv)
{  
  
   if (argc == 1)
     return -1;
   
   char path[256];
   
   strcpy(path,"C:\\WINDOWS\\");
   strncat(path, argv[1], sizeof(path)/2);
   strcat(path,"\\cmd.exe");

   if (CreateFile(path, 0, 0, NULL, OPEN_EXISTING, 0, NULL) != INVALID_HANDLE_VALUE)
     jump_level(7);
   else
     printf("%s doesn't exist!\n",path);
   
   return 0;
}

   
  
