#ifdef PLUGIN1GETTINGHARDWAREINFORMATION_EXPORTS
#define PLUGIN1GETTINGHARDWAREINFORMATION_API __declspec(dllexport)
#else
#define PLUGIN1GETTINGHARDWAREINFORMATION_API __declspec(dllimport)
#endif


#pragma warning(disable : 4996)
#include <WinNT.h>
#include <IntSafe.h>
#include <string.h>
#include <stdio.h>


#ifdef __cplusplus
extern "C"
{
#endif

	PLUGIN1GETTINGHARDWAREINFORMATION_API const char* __stdcall getName();
	PLUGIN1GETTINGHARDWAREINFORMATION_API BOOLEAN __stdcall getAuthor(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);
	PLUGIN1GETTINGHARDWAREINFORMATION_API BOOLEAN __stdcall getDescription(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);
	PLUGIN1GETTINGHARDWAREINFORMATION_API VOID __stdcall execute();

#ifdef __cplusplus
};
#endif