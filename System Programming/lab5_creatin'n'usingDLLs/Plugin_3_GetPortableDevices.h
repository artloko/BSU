#pragma once


#pragma warning(disable : 4996)
#include <Windows.h>
#include <stdio.h>
#include <VersionHelpers.h>
#include <WinNT.h>
#include <IntSafe.h>

const char* getNameThird();
BOOLEAN getAuthorThird(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);
BOOLEAN getDescriptionThird(LPSTR buffer, DWORD dwBufferSize, DWORD* pdwBytesWritten);
VOID executeThird();