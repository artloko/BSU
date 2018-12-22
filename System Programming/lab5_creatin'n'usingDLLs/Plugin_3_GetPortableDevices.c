
#include "stdafx.h"
#include "Plugin_3_GetPortableDevices.h"


static const char* AUTHOR = "artloko";
static const int AUTHORLENGTH = 8;

static const char* DESCRIPTION = "Method Execute() gets your Windows Version";
static const int DESCRIPTIONLENGTH = 63;

const char* getNameThird()
{
	return "Plugin_3_GetPortableDevices.lib";
}

BOOLEAN getAuthorThird(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten)
{
	if (AUTHORLENGTH > dwBufferSize)
		return false;

	*pdwBytesWritten = AUTHORLENGTH;
	strcpy(buffer, AUTHOR);
	return true;
}

BOOLEAN getDescriptionThird(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten)
{
	if (DESCRIPTIONLENGTH > dwBufferSize)
		return false;

	*pdwBytesWritten = DESCRIPTIONLENGTH;
	buffer = strcpy(buffer, DESCRIPTION);
	return true;
}


void executeThird()
{
	if (IsWindows10OrGreater())
		printf("Your Windows version is 10");
	else if (IsWindows8Point1OrGreater())
		printf("Your Windows version is 8.1");
	else if (IsWindows8OrGreater())
		printf("Your Windows version is 8");
	else if (IsWindows7SP1OrGreater())
		printf("Your Windows version is 7SP1");
	else if (IsWindows7OrGreater())
		printf("Your Windows version is 7");
	else
		printf("Sorry, your version is too old");
}
