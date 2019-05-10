#include <stdbool.h>
#include <Windows.h>
#include <stdio.h>
#include "Plugin_1_GettingHardWareInformation.h"
#include "../static/static.h"

typedef BOOLEAN(__stdcall *getInfo)(LPSTR, DWORD, DWORD*);
typedef VOID(__stdcall *executeSecond)();
typedef const char*(__stdcall *getNameSecond)();


HMODULE hModuleSecond;
getInfo fGetAuthorSecond;
getInfo fGetDescriptionSecond;
executeSecond fExecuteSecond;
getNameSecond fGetNameSecond;

BOOLEAN LoadPlugin()
{
	
	if ((hModuleSecond = LoadLibrary(TEXT("C:\\Users\\USER\\Documents\\Visual Studio 2017\\Projects\\DLLs\\Plugin_2_GetMemory\\Debug\\Plugin_2_GetMemory.dll"))) != NULL)
	{
		fGetAuthorSecond = (getInfo)GetProcAddress(hModuleSecond, "getAuthorSecond");
		fGetDescriptionSecond = (getInfo)GetProcAddress(hModuleSecond, "getDescriptionSecond");
		fExecuteSecond = (executeSecond)GetProcAddress(hModuleSecond, "executeSecond");
		fGetNameSecond = (getNameSecond)GetProcAddress(hModuleSecond, "getNameSecond");
		if (fGetAuthorSecond && fGetDescriptionSecond && fExecuteSecond && fGetNameSecond)
			return true;
	}
	return false;
}

BOOLEAN FreePlugin(HMODULE hModuleSecond)
{
	if (FreeLibrary(hModuleSecond))
		return true;
	return false;
}

VOID ExecutePlugin()
{
	BOOL isFinished = 0;
	char choice = 0;
	while (!isFinished)
	{
		printf("(1) - Get hardware information\n(2) - Get total memory\n(3) - Get Windows Version\n(Other) - EXIT\n	Your choice is: ");
		scanf_s(" %c", &choice);

		switch (choice)
		{
		case '1':
			execute();
			break;
		case '2':
			if (hModuleSecond != NULL)
				fExecuteSecond();
			else
				printf("Error, cannot execute an explicit plugin.\n\n");
			break;
		case '3':
			executeThird();
			break;
		default:
			return;
			break;
		}
	}

}

int main()
{
	BOOL isFinished = 0;
	char choice = 0;
	while (!isFinished)
	{
		printf("(1) - Load plugin\n(2) - Free plugin\n(3) - Get list of plugins\n(4) - Get info of each plugin\n(5) - Execute plugin\n(Other) - EXIT\n	Your choice is: ");
		scanf_s(" %c", &choice);

		switch (choice)
		{
		case '1':
			if (!LoadPlugin())
				printf("Error, cannot load an explicit plugin.\n\n");
			else
				printf("Plugin is loaded sucessfully.\n\n");
			break;
		case '2':
			if (!FreePlugin(hModuleSecond))
				printf("Error, cannot free plugin.\n\n");
			else
				printf("Plugins is freed sucessfully.\n\n");
			break;
		case '3':
			printf(" %s\n", getName());
			printf(" %s\n", getNameThird());
			if (hModuleSecond != NULL)
				printf(" %s\n", fGetNameSecond());
			else
				printf("Error, cannot get an explicit plugin.\n");
			printf("\n");
			break;
		case '4':

			break;
		case '5':
			ExecutePlugin();
			break;
		default:
			isFinished = 1;
			break;
		}
	}
}
