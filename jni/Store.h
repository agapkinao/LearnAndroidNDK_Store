#ifndef _STORE_H_
#define _STORE_H_

# include "jni.h"

const int ENTRIES_MAX_COUNT = 14;

enum StoreType
{
	Integer,
	String
};

union StoreValue
{
	char* sString;
	int iInt;
};

struct StoreEntry
{
	char* sKey;
	StoreType Type;
	StoreValue Value;
};

struct Store
{
	StoreEntry Entries[ENTRIES_MAX_COUNT];
	int iCount;
};

// Ìועמהû
int isEntryValid(JNIEnv* pEnv, StoreEntry* pEntry, StoreType pType);
StoreEntry* allocateEntry(JNIEnv* pEnv, Store* pStore, jstring pKey);
StoreEntry* findEntry(JNIEnv* pEnv, Store* pStore, jstring pKey, int* pError);
void releaseEntryValue(JNIEnv* pEnv, StoreEntry* pEntry);

#endif
