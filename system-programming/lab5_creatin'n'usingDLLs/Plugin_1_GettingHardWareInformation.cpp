// Plugin_1_GettingHardWareInformation.cpp : Defines the exported functions for the DLL application.
//

#include "stdafx.h"
#include "Plugin_1_GettingHardWareInformation.h"


static const char* AUTHOR = "artloko";
static const int AUTHORLENGTH = 8;

static const char* DESCRIPTION = "Method Execute() gets the hardware information";
static const int DESCRIPTIONLENGTH = 63;

const char* __stdcall getName()
{
	return "Plugin_1_GettingHardWareInformation.dll";
}

BOOLEAN __stdcall getAuthor(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten)
{
	if (AUTHORLENGTH > dwBufferSize)
		return false;

	*pdwBytesWritten = AUTHORLENGTH;
	strcpy(buffer, AUTHOR);
	return true;
}

BOOLEAN __stdcall getDescription(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten)
{
	if (DESCRIPTIONLENGTH > dwBufferSize)
		return false;

	*pdwBytesWritten = DESCRIPTIONLENGTH;
	strcpy(buffer, DESCRIPTION);
	return true;
}

VOID __stdcall execute()
{
	SYSTEM_INFO siSysInfo;

	// Copy the hardware information to the SYSTEM_INFO structure. 

	GetSystemInfo(&siSysInfo);

	// Display the contents of the SYSTEM_INFO structure. 

	printf("Hardware information: \n");
	printf("  OEM ID: %u\n", siSysInfo.dwOemId);
	printf("  Number of processors: %u\n",
		siSysInfo.dwNumberOfProcessors);
	printf("  Page size: %u\n", siSysInfo.dwPageSize);
	printf("  Processor type: %u\n", siSysInfo.dwProcessorType);
	printf("  Minimum application address: %lx\n",
		siSysInfo.lpMinimumApplicationAddress);
	printf("  Maximum application address: %lx\n",
		siSysInfo.lpMaximumApplicationAddress);
	printf("  Active processor mask: %u\n",
		siSysInfo.dwActiveProcessorMask);
}