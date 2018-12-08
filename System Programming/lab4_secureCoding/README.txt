This is a hacking exercise.

You should start from level1 and move up.
At each level examine the source code (e.g. level1.cpp)
and run the corresponding executable (e.g. level.exe)
with the input that will take you to the call to function
jump_level(). Upon success the shell prompt will change to the 
next level. When you solve the hacking challange explain what 
type of vulnerability did you exploit and how.

You are not allowed to use debugging to go to next level.

Example:

D:\SCW\levels>type level1.cpp
#include "jump_level.h"


int main(int argc, char **argv)
{
        if (argc != 2)
                return -1;

        char c = strlen(argv[1]);

        if (c < 0)
                jump_level(2);
        else
                printf("maybe next time :)\n");

   return 0;
}

Which input will bring the program flow to jump_level(2)?


The following commands will simplify your work:

- Automating input generation
	perl -e "print 'A' x 256”		- generates 256 ‘A’
	perl -e " print \"\x0f\x10\x40\" "	- print binary values
	perl -e "print 'A' x 84; print \"\x0f\x10\x40\" " – concatenate strings	
- Providing argument to a program
	perl -e "print 'A' x 84" > x &&  set /p x=<x && level1 %x%




GOOD LUCK !!!
