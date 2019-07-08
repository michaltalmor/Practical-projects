/*
 * keyValuePair.c
 *
 *  Created on: Nov 29, 2018
 *      Author: ise
 */

#include "keyValuePair.h"
#include "Defs.h"

struct keyValuePair_s {
	Element key;
	Element value;
	copyFunction keyCopyFunc;
	copyFunction valueCopyFunc;
	freeFunction keyFreeFunc;
	freeFunction valueFreeFunc;
	printFunction keyPrintFunc;
	printFunction valuePrintFunc;
	equalFunction keyEqualFunc;

};

keyValuePair createKeyValuePair(Element key, Element value, copyFunction keyCopyFunc, copyFunction valueCopyFunc, freeFunction keyFreeFunc, freeFunction valueFreeFunc, printFunction keyPrintFunc, printFunction valuePrintFunc, equalFunction keyEqualFunc) {

	if (key == NULL)
		return NULL;

	if (keyCopyFunc == NULL)
		return NULL;

	if (valueCopyFunc == NULL)
		return NULL;

	if (keyFreeFunc == NULL)
		return NULL;

	if (valueFreeFunc == NULL)
		return NULL;

	if (keyPrintFunc == NULL)
		return NULL;

	if (valuePrintFunc == NULL)
		return NULL;

	if (keyEqualFunc == NULL)
		return NULL;

	keyValuePair pair = (keyValuePair)malloc(sizeof(struct keyValuePair_s));

	if (pair == NULL)
		return NULL;

	pair->keyCopyFunc = keyCopyFunc;
	pair->valueCopyFunc = valueCopyFunc;
	pair->keyFreeFunc = keyFreeFunc;
	pair->valueFreeFunc = valueFreeFunc;
	pair->keyPrintFunc = keyPrintFunc;
	pair->valuePrintFunc = valuePrintFunc;
	pair->keyEqualFunc = keyEqualFunc;

	pair->key = pair->keyCopyFunc(key);

	if (value != NULL)
		pair->value = pair->valueCopyFunc(value);
	else
		pair->value=NULL;

	return pair;

}

status destroyKeyValuePair(keyValuePair pair) {

	if (pair == NULL)
		return failure;

	if (pair->value != NULL)
		if (pair->valueFreeFunc(pair->value) == failure)
			return failure;

	if (pair->key != NULL)
		if (pair->keyFreeFunc(pair->key) == failure)
			return failure;

	return success;

}

void displayValue(keyValuePair pair) {

	if (pair != NULL)
		if (pair->value != NULL)
			pair->valuePrintFunc(pair->value);

}

void displayKey(keyValuePair pair) {

	if (pair != NULL)
		if (pair->key != NULL)
			pair->keyPrintFunc(pair->key);

}

Element getValue(keyValuePair pair) {

	if (pair == NULL)
		return NULL;

	if (pair->value == NULL)
		return NULL;

	return pair->value;

}

Element getKey(keyValuePair pair) {

	if (pair == NULL)
		return NULL;

	if (pair->key == NULL)
		return NULL;

	return pair->key;

}

bool isEqualKey(keyValuePair pair1, keyValuePair pair2) {

	if (pair1 == NULL)
		return false;

	if (pair2 == NULL)
		return false;

	if ((pair1->keyEqualFunc(pair1->key, pair2->key)) == true)
		return true;

	return false;

}

Element copyPair(Element elem) {

	if (elem == NULL)
		return NULL;

	keyValuePair pair = createKeyValuePair(((keyValuePair)elem)->key,((keyValuePair)elem)->value,((keyValuePair)elem)->keyCopyFunc,((keyValuePair)elem)->valueCopyFunc,((keyValuePair)elem)->keyFreeFunc,((keyValuePair)elem)->valueFreeFunc,((keyValuePair)elem)->keyPrintFunc,((keyValuePair)elem)->valuePrintFunc,((keyValuePair)elem)->keyEqualFunc);

	return pair;

}

status freePair(Element elem) {

	if (elem == NULL)
		return failure;

	return destroyKeyValuePair(((keyValuePair)elem));

}

status printValue(Element elem) {

	if (elem == NULL)
		return failure;

	displayValue(((keyValuePair)elem));

	return success;

}

bool equalKey(Element elem1, Element elem2) {

	if (elem1 == NULL)
		return false;

	if (elem2 == NULL)
		return false;

	return isEqualKey(((keyValuePair)elem1), ((keyValuePair)elem2));

}
