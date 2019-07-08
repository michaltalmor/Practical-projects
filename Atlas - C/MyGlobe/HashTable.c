/*
 * HashTable.c
 *
 *  Created on: Dec 3, 2018
 *      Author: ise
 */


#include "HashTable.h"
#include "Defs.h"
#include "LinkedList.h"
#include "keyValuePair.h"

struct hashTable_s {
	int hashNumber;
	int elementsInTable;
	int* elementsInLists;
	copyFunction keyCopyFunc;
	copyFunction valueCopyFunc;
	freeFunction keyFreeFunc;
	freeFunction valueFreeFunc;
	printFunction keyPrintFunc;
	printFunction valuePrintFunc;
	equalFunction keyEqualFunc;
	transformIntoNumberFunction keyToIntFunc;
	struct LinkedList_s ** hTable;

};

hashTable createHashTable(copyFunction copyKey, freeFunction freeKey, printFunction printKey, copyFunction copyValue, freeFunction freeValue, printFunction printValue, equalFunction equalKey, transformIntoNumberFunction transformKeyIntoNumber, int hashNumber) {

	if (copyKey == NULL)
		return NULL;

	if (freeKey == NULL)
		return NULL;

	if (printKey == NULL)
		return NULL;

	if (copyValue == NULL)
		return NULL;

	if (freeValue == NULL)
		return NULL;

	if (printValue == NULL)
		return NULL;

	if (equalKey == NULL)
		return NULL;

	if (transformKeyIntoNumber == NULL)
		return NULL;

	hashTable table = (hashTable)malloc(sizeof(struct hashTable_s));

	if (table == NULL)
		return NULL;

	table->elementsInLists = (int*)malloc(sizeof(int)*hashNumber);

	if (table->elementsInLists == NULL) {
		free(table);
		table = NULL;
		return NULL;
	}

	table->hTable = NULL;
	table->elementsInTable = 0;
	table->hashNumber = hashNumber;
	table->keyCopyFunc = copyKey;
	table->keyFreeFunc = freeKey;
	table->keyPrintFunc = printKey;
	table->valueCopyFunc = copyValue;
	table->valueFreeFunc = freeValue;
	table->valuePrintFunc = printValue;
	table->keyEqualFunc = equalKey;
	table->keyToIntFunc = transformKeyIntoNumber;

	for (int i = 0; i < table->hashNumber; i++)
		table->elementsInLists[i] = 0;

	return table;

}

status destroyHashTable(hashTable table) {

	if (table == NULL)
		return failure;


	if (table->elementsInTable != 0)
		for (int i = 0; i < table->hashNumber; i++)
			if (table->elementsInLists[i] != 0) {
				destroyList(table->hTable[i]); // can return failure on empty lists
				free(table->hTable[i]);
			}

	free(table->elementsInLists);
	table->elementsInLists = NULL;

	free(table->hTable);
	table->hTable = NULL;

	return success;
}


status addToHashTable(hashTable table, Element key, Element value) {


	if (table == NULL)
		return failure;

	if (key == NULL)
		return failure;

	if (value == NULL)
		return failure;

	keyValuePair pair = createKeyValuePair(key, value, table->keyCopyFunc, table->valueCopyFunc, table->keyFreeFunc, table->valueFreeFunc, table->keyPrintFunc, table->valuePrintFunc, table->keyEqualFunc);

	if (pair == NULL)
		return failure;

	int index = (table->keyToIntFunc(key))%(table->hashNumber);

	// Checks if hash table is empty
	if (table->elementsInTable == 0) {

		table->hTable = (LinkedList*)malloc(sizeof(LinkedList));

		if (table->hTable == NULL) {
			destroyKeyValuePair(pair);
			free(pair);
			pair = NULL;
			return failure;
		}

		table->hTable[index] = createLinkedList(copyPair, freePair, equalKey, printValue);

		if (table->hTable[index] == NULL) {
			destroyKeyValuePair(pair);
			free(pair);
			pair = NULL;
			free(table->hTable);
			table->hTable = NULL;
			return failure;
		}

		if (appendNode(table->hTable[index], pair) == failure) {
			destroyKeyValuePair(pair);
			free(pair);
			pair = NULL;
			destroyList(table->hTable[index]);
			free(table->hTable[index]);
			table->hTable[index] = NULL;
			free(table->hTable);
			table->hTable = NULL;
			return failure;

		}
	}
	else
	{
		// Checks if there are elements in the specific cell
		if (table->elementsInLists[index] == 0) {

			table->hTable[index] = createLinkedList(copyPair, freePair, equalKey, printValue);

			if (table->hTable[index] == NULL) {
				destroyKeyValuePair(pair);
				free(pair);
				pair = NULL;
				return failure;
			}

			if (appendNode(table->hTable[index], pair) == failure) {
				destroyKeyValuePair(pair);
				free(pair);
				pair = NULL;
				destroyList(table->hTable[index]);
				free(table->hTable[index]);
				table->hTable[index] = NULL;
				return failure;
			}

		}
		else
		{
			if (appendNode(table->hTable[index], pair) == failure) {
				destroyKeyValuePair(pair);
				free(pair);
				pair = NULL;
				return failure;
			}
		}
	}

	destroyKeyValuePair(pair);
	free(pair);
	pair = NULL;

	table->elementsInTable++;
	table->elementsInLists[index]++;

	return success;

}

Element lookupInHashTable(hashTable table, Element key) {

	if (table == NULL)
		return NULL;

	if (key == NULL)
		return NULL;

	if (table->elementsInTable == 0)
		return NULL;

	int index = (table->keyToIntFunc(key))%(table->hashNumber);

	if (table->elementsInLists[index] == 0)
		return NULL;

	keyValuePair pair = createKeyValuePair(key, NULL, table->keyCopyFunc, table->valueCopyFunc, table->keyFreeFunc, table->valueFreeFunc, table->keyPrintFunc, table->valuePrintFunc, table->keyEqualFunc);

	Element result = searchInList(table->hTable[index], pair);

	destroyKeyValuePair(pair);
	free(pair);
	pair = NULL;

	if (result != NULL)
		return getValue(result);
	else
		return NULL;
}

status removeFromHashTable(hashTable table, Element key) {

	if (table == NULL)
		return failure;

	if (key == NULL)
		return failure;

	if (table->elementsInTable == 0)
		return failure;

	int index = (table->keyToIntFunc(key))%(table->hashNumber);

	if (table->elementsInLists[index] == 0)
		return failure;

	keyValuePair pair = createKeyValuePair(key, NULL, table->keyCopyFunc, table->valueCopyFunc, table->keyFreeFunc, table->valueFreeFunc, table->keyPrintFunc, table->valuePrintFunc, table->keyEqualFunc);

	Element result = searchInList(table->hTable[index], pair);

	if (result == NULL)
		return failure;

	status st = deleteNode(table->hTable[index], result);

	destroyKeyValuePair(pair);
	free(pair);
	pair = NULL;

	table->elementsInTable--;
	table->elementsInLists[index]--;

	// Destroys the list if no elements in it
	if (table->elementsInLists[index] == 0) {
		destroyList(table->hTable[index]);
		free(table->hTable[index]);
		table->hTable[index] = NULL;
	}

	// Destroys the array if no elements at all
	if (table->elementsInTable == 0) {
		free(table->hTable);
		table->hTable = NULL;
	}

	return st;

}

status displayHashElements(hashTable table) {

	if (table == NULL)
		return failure;

	if (table->elementsInTable != 0)
		for (int i = 0; i <table->hashNumber; i++)
			if (table->elementsInLists[i] != 0)
				displayList(table->hTable[i]);

	return success;
}
