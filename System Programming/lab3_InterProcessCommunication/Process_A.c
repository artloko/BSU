#include <windows.h>
#include <tlhelp32.h>
#include <stdio.h>
#define MAXLEN 100													

DWORD getProcessID(LPCWSTR process_name) {
	DWORD processID = 0;
	HANDLE snapHandle;
	PROCESSENTRY32 processEntry;
	if ((snapHandle = CreateToolhelp32Snapshot(TH32CS_SNAPPROCESS, 0)) == INVALID_HANDLE_VALUE) {
		return 0;
	}

	processEntry.dwSize = sizeof(PROCESSENTRY32);
	Process32First(snapHandle, &processEntry);
	do {
		if (strcmp(processEntry.szExeFile, process_name) == 0) {
			return processEntry.th32ProcessID;
		}
	} while (Process32Next(snapHandle, &processEntry));

	if (snapHandle != INVALID_HANDLE_VALUE) {
		CloseHandle(snapHandle);
	}
	return 0;
}

char SendAdress(DWORD szAdress)
{
	HANDLE hNamedPipe;
	LPCWSTR pipeName = L"\\\\.\\pipe\\address_pipe";
	hNamedPipe = CreateFile(
		pipeName,
		GENERIC_WRITE,
		FILE_SHARE_READ,
		(LPSECURITY_ATTRIBUTES)NULL,
		OPEN_EXISTING,
		0, (HANDLE)NULL
	);

	if (hNamedPipe == INVALID_HANDLE_VALUE)
	{
		printf("Connection with the named pipe failed.\n");
		return 0;
	}
	DWORD dwBytesWritten;
	printf("Wating for server read data...\n");
	if (!WriteFile(
		hNamedPipe,
		&szAdress,
		sizeof(szAdress),
		&dwBytesWritten,
		(LPOVERLAPPED)NULL))
	{
		printf("Writing to the named pipe failed\n");
		CloseHandle(hNamedPipe);
		return 0;
	}
	CloseHandle(hNamedPipe);
	printf("Server read data.\n");
	return 1;
}


void main()
{
	DWORD szSize = sizeof(char)*MAXLEN;
	char *a = (char*)malloc(szSize);
	printf("Enter string: ");
	gets_s(a, MAXLEN - 1);
	HANDLE hProcess = OpenProcess(PROCESS_ALL_ACCESS, FALSE, getProcessID(L"pr_B.exe"));
	LPVOID szAddress = VirtualAllocEx(hProcess, 0, szSize, MEM_RESERVE | MEM_COMMIT, PAGE_EXECUTE_READWRITE);
	WriteProcessMemory(hProcess, szAddress, a, szSize, 0);
	SendAdress((DWORD)szAddress);
	free(a);
	VirtualFreeEx(hProcess, szAddress, 0, MEM_RELEASE);
