#pragma once


typedef   unsigned int IPADDRESS;
typedef struct _DNS_ENTRY
{
	char*        domainName;
	IPADDRESS    ip;
	struct _DNS_ENTRY *next;
} DNS_ENTRY;


/*************************************************************************
hash_table contains an array of pointers to entries,
where entry has:
id - as a key
domainName - as a value
*************************************************************************/
typedef struct _HASH_TABLE{
	int size;
	int count;
	DNS_ENTRY** entries;
} HASH_TABLE;


/*************************************************************************
FUNCTION:
	new_entry()

DESCRIPTION:
	allocate memory for new entry and set the key and value to it

PARAMETERS:
	k - IPADDRESS, key
	v - domainName, value

RETURN VALUE:
	just created entry
*************************************************************************/
static DNS_ENTRY* new_entry(IPADDRESS ip, const char* domainName);


/*************************************************************************
FUNCTION:
	new_ht()

DESCRIPTION:
	allocate memory for new hashtable

PARAMETERS:
	none

RETURN VALUE:
	just created hashtable
*************************************************************************/
HASH_TABLE* new_ht();


/*************************************************************************
FUNCTION:
	del_entry()

DESCRIPTION:
	delete entry and free memory

PARAMETERS:
	entry - entry to delete

RETURN VALUE:
	none
*************************************************************************/
static void del_entry(DNS_ENTRY* entry);


/*************************************************************************
FUNCTION:
	del_ht()

DESCRIPTION:
	delete hashtable and free memory

PARAMETERS:
	hash_table - hashtable to delete

RETURN VALUE:
	none
*************************************************************************/
void del_ht(HASH_TABLE* hash_table);


/*************************************************************************
FUNCTION:
	hash()

DESCRIPTION:
	calculates the hashcode based on the hostname, variable and number of buckets in the hashtable

PARAMETERS:
	str - hostname
	a - variable, must be more than the size of alphabet (in our case more than 128, because we're hashing ASCII strings)
	num_buckets - number of buckets in the hashtable

RETURN VALUE:
	hashcode
*************************************************************************/
static int hash(const char* str, const int num_buckets);

void insert(HASH_TABLE* ht, IPADDRESS ip, const char* domainName);
int search(HASH_TABLE* ht, const char* domainName);
