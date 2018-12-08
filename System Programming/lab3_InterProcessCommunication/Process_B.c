#include <windows.h> 
#include <stdio.h>

int main() {
	LPCWSTR pipeName = L"\\\\.\\pipe\\address_pipe";
	HANDLE hNamedPipe;
	hNamedPipe = CreateNamedPipe(
		pipeName,
		PIPE_ACCESS_INBOUND,
		PIPE_TYPE_MESSAGE |
		PIPE_READMODE_MESSAGE |
		PIPE_WAIT,
		1,
		sizeof(DWORD),
		sizeof(DWORD),
		1,
		(LPSECURITY_ATTRIBUTES)NULL
	);
	if (hNamedPipe == INVALID_HANDLE_VALUE)
	{
		printf("Can't create name pipe.\n");
		return 1;
	}
	printf("Wating client write data!\n");
	ConnectNamedPipe(hNamedPipe, NULL);
	DWORD address;
	DWORD dwBytesRead;
	ReadFile(hNamedPipe,
		&address,
		sizeof(address),
		&dwBytesRead,
		(LPOVERLAPPED)NULL
	);
	printf("String get: %s\n", (char*)address);
	CloseHandle(hNamedPipe);
	return 0;
}