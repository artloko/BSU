#include <stdlib.h>
#include <stdio.h>
#include <string.h>
#include "my_hashtable.h"

static DNS_ENTRY DELETED_ENTRY = { NULL, NULL };


static DNS_ENTRY* new_entry(IPADDRESS ip, const char* domainName) {
	DNS_ENTRY* entry;

	if ((entry = malloc(sizeof(DNS_ENTRY))) == NULL) {
		return NULL;
	}

	if ((entry->domainName = strdup(domainName)) == NULL) {
		return NULL;
	}

	entry->ip = ip;
	entry->next = NULL;

	return entry;
}

HASH_TABLE* new_ht() {
	HASH_TABLE* hash_table = NULL;
	int i;

	if ((hash_table = malloc(sizeof(HASH_TABLE))) == NULL) {
		return NULL;
	}

	hash_table->size = 15000;
	hash_table->count = 0;

	if ((hash_table->entries = malloc(sizeof(DNS_ENTRY*) * hash_table->size)) == NULL) {
		return NULL;
	}
	
	for (int i = 0; i < hash_table->size; i++) {
		hash_table->entries[i] = NULL;
	}

	return hash_table;
}

static void del_entry(DNS_ENTRY* entry) {
	free(entry->domainName);
	free(entry->next);
	free(entry);
}

void del_ht(HASH_TABLE* hash_table) {
	int i;
	for (i = 0; i < hash_table->size; i++) {
		DNS_ENTRY* entry = hash_table->entries[i];
		if (entry != NULL) {
			del_entry(entry);
		}
	}
	free(hash_table->entries);
	free(hash_table);
}

static int hash(const char* str, const int num_buckets) {
	unsigned int h = 20, A = 212513, B = 32465;
	while (*str) {
		h = (h * A) ^ (str[0] * B);
		str++;
	}
	return h % num_buckets;
}


void insert(HASH_TABLE* ht, IPADDRESS ip, const char* domainName) {
	int bin = 0;
	DNS_ENTRY *entry = NULL;
	DNS_ENTRY *next = NULL;
	DNS_ENTRY *last = NULL;

	bin = hash(domainName, ht->size);

	next = ht->entries[bin];

	while (next != NULL && strcmp(domainName, next->domainName) > 0) {
		last = next;
		next = next->next;
	}

	if (next == NULL || strcmp(domainName, next->domainName) != 0) {
		entry = new_entry(ip, domainName);

		if (next == ht->entries[bin]) {
			entry->next = next;
			ht->entries[bin] = entry;
		}
		else if (next == NULL) {
			last->next = entry;
		}
		else {
			entry->next = next;
			last->next = entry;
		}
	}

}


int search(HASH_TABLE* ht, const char* domainName) {
	int bin = 0;
	DNS_ENTRY *entry;

	bin = hash(domainName, ht->size);

	entry = ht->entries[bin];
	while (entry != NULL && strcmp(domainName, entry->domainName) > 0) {
		entry = entry->next;
	}

	if (entry == NULL) {
		return NULL;
	}
	else {
		return entry->ip;
	}

}
