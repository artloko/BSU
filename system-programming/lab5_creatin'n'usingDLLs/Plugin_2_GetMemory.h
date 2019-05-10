#ifdef PLUGIN2GETMEMORY_EXPORTS
#define PLUGIN2GETMEMORY_API __declspec(dllexport)
#else
#define PLUGIN2GETMEMORY_API __declspec(dllimport)
#endif

#pragma warning(disable : 4996)
#include <WinNT.h>
#include <IntSafe.h>
#include <stdio.h>
#include <intrin.h>


#ifdef __cplusplus
extern "C"
{
#endif

	PLUGIN2GETMEMORY_API LPSTR __stdcall getNameSecond();
	PLUGIN2GETMEMORY_API BOOLEAN __stdcall getAuthorSecond(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);
	PLUGIN2GETMEMORY_API BOOLEAN __stdcall getDescriptionSecond(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);
	PLUGIN2GETMEMORY_API VOID __stdcall executeSecond();

#ifdef __cplusplus
};
#endif