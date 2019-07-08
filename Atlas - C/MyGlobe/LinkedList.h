/*
 * LinkedList.h
 *
 *  Created on: Nov 29, 2018
 *      Author: ise
 */

#ifndef LINKEDLIST_H_
#define LINKEDLIST_H_

#include "Defs.h"

typedef struct LinkedList_s* LinkedList;
typedef struct Node_s* Node;

LinkedList createLinkedList(copyFunction, freeFunction, equalFunction, printFunction);
status destroyList(LinkedList);
status appendNode(LinkedList, Element);
status deleteNode(LinkedList, Element);
status displayList(LinkedList);
Element searchInList(LinkedList, Element);


#endif /* LINKEDLIST_H_ */
