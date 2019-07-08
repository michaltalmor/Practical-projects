/*
 * keyValuePair.h
 *
 *  Created on: Nov 29, 2018
 *      Author: ise
 */

#ifndef SRC_KEYVALUEPAIR_H_
#define SRC_KEYVALUEPAIR_H_

#include "Defs.h"

typedef struct keyValuePair_s* keyValuePair;

keyValuePair createKeyValuePair(Element, Element, copyFunction, copyFunction, freeFunction, freeFunction, printFunction, printFunction, equalFunction);
status destroyKeyValuePair(keyValuePair);
void displayValue(keyValuePair);
void displayKey(keyValuePair);
Element getValue(keyValuePair);
Element getKey(keyValuePair);
bool isEqualKey(keyValuePair, keyValuePair);

Element copyPair(Element);
status freePair(Element);
status printValue(Element);
bool equalKey(Element, Element);

#endif /* SRC_KEYVALUEPAIR_H_ */
