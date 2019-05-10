// Plugin_2_GetMemory.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include "Plugin_2_GetMemory.h"


static const char* AUTHOR = "artloko";
static const int AUTHORLENGTH = 8;

static const char* DESCRIPTION = "Method Execute() prints the total system memory";
static const int DESCRIPTIONLENGTH = 63;

LPSTR _stdcall getNameSecond()
{
	return (LPSTR)"Plugin_2_GetMemory.dll";
}

BOOLEAN __stdcall getAuthorSecond(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten)
{
	if (AUTHORLENGTH > dwBufferSize)
		return false;

	pdwBytesWritten = 0;
	for (DWORD i = 0; i < dwBufferSize; i++)
	{
		pdwBytesWritten++;
		buffer[i] = AUTHOR[i];
	}
	return true;
}

BOOLEAN __stdcall getDescriptionSecond(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten)
{
	if (DESCRIPTIONLENGTH > dwBufferSize)
		return false;

	pdwBytesWritten = 0;
	for (DWORD i = 0; i < dwBufferSize; i++)
	{
		pdwBytesWritten++;
		buffer[i] = AUTHOR[i];
	}
	return true;
}

VOID __stdcall executeSecond()
{
	int CPUInfo[4] = { -1 };
	unsigned   nExIds, i = 0;
	char CPUBrandString[0x40];
	// Get the information associated with each extended ID.
	__cpuid(CPUInfo, 0x80000000);
	nExIds = CPUInfo[0];
	for (i = 0x80000000; i <= nExIds; ++i)
	{
		__cpuid(CPUInfo, i);
		// Interpret CPU brand string
		if (i == 0x80000002)
			memcpy(CPUBrandString, CPUInfo, sizeof(CPUInfo));
		else if (i == 0x80000003)
			memcpy(CPUBrandString + 16, CPUInfo, sizeof(CPUInfo));
		else if (i == 0x80000004)
			memcpy(CPUBrandString + 32, CPUInfo, sizeof(CPUInfo));
	}
	//string includes manufacturer, model and clockspeed
	printf("CPU Type: %s\n", CPUBrandString);


	SYSTEM_INFO sysInfo;
	GetSystemInfo(&sysInfo);
	printf("Number of Cores: %u\n", sysInfo.dwNumberOfProcessors);

	MEMORYSTATUSEX statex;
	statex.dwLength = sizeof(statex);
	GlobalMemoryStatusEx(&statex);
	printf("Total System Memory: %u MB\n", (statex.ullTotalPhys / 1024) / 1024);
}

