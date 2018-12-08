
/*************************************************************************
   LAB 1                                                                

    Edit this file ONLY!

*************************************************************************/



#include "dns.h"
#include "my_hashtable.h"
#include <stdio.h>
#include <stdlib.h>

#define OPEN_MODE "r"


DNSHandle InitDNS()
{
	HASH_TABLE* ht = new_ht();
	DNSHandle hDNS = (DNSHandle)ht;
	if (hDNS != NULL) {
		return hDNS;
	}
	return INVALID_DNS_HANDLE;
}

void LoadHostsFile(DNSHandle hDNS, const char* hostsFilePath )
{
	FILE* fInput = NULL;

	unsigned int ip1 = 0, ip2 = 0, ip3 = 0, ip4 = 0;
	char* buffer = NULL;

	if (hDNS == NULL) {
		return;
	}

	fInput = fopen(hostsFilePath, OPEN_MODE);
	if (fInput == NULL) {
		return;
	}

	buffer = (char*)malloc(201);

	for (; !feof(fInput);)
	{
		unsigned int ip1 = 0, ip2 = 0, ip3 = 0, ip4 = 0;

		if (5 != fscanf_s(fInput, "%d.%d.%d.%d %s", &ip1, &ip2, &ip3, &ip4, buffer, 200))
			continue;

		IPADDRESS ip = (ip1 & 0xFF) << 24 |
			(ip2 & 0xFF) << 16 |
			(ip3 & 0xFF) << 8 |
			(ip4 & 0xFF);


		if (buffer[0])
		{
			insert((HASH_TABLE*)hDNS, ip, buffer);
		}
	}
	free(buffer);
	fclose(fInput);
}

IPADDRESS DnsLookUp( DNSHandle hDNS, const char* hostName )
{
	IPADDRESS ip = 0;
	ip = search(((HASH_TABLE*)hDNS), hostName);
	if (ip != NULL) {
		return ip;
	}
    return INVALID_IP_ADDRESS;
}

void ShutdownDNS( DNSHandle hDNS )
{
	del_ht((HASH_TABLE*)hDNS);
}