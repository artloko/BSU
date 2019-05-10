#include "jump_level.h"

#define MAXLOGIN 16
#define MAXPASS 32

char check_user(char *,char *);

int main(int argc, char *argv[])
{
	char login[MAXLOGIN] = {0};
	char password[MAXPASS] = {0};
   
	if (argc != 3) {
		fprintf(stderr,"usage: %s <login> <password>\n",argv[0]);
		return -1;
	}

	strncpy(login,argv[1],MAXLOGIN);
	strncpy(password,argv[2],MAXPASS);

	if (check_user(login,password) == 0)
		printf("maybe next time :)\n");
	else
		jump_level(10);
   
	return 0;
}

char check_user(char *login,char *password)
{
	unsigned int i = 0;
	char key[MAXPASS + 5];

	memset(key,0,MAXPASS + 5);
	strcpy(key,"key=");
	strcat(key,password);
	return i;
}

