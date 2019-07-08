/*
 * LinkedList.c
 *
 *  Created on: Nov 29, 2018
 *      Author: ise
 */

#include "LinkedList.h"
#include "Defs.h"

struct Node_s {
	Element value;
	struct Node_s * next;
};

struct LinkedList_s {
	struct Node_s * head;
	struct Node_s * tail;
	int numberOfNodes;
	copyFunction copyFunc;
	freeFunction freeFunc;
	equalFunction equalFunc;
	printFunction printFunc;
};

LinkedList createLinkedList(copyFunction copyFunc, freeFunction freeFunc, equalFunction equalFunc, printFunction printFunc) {

	if (copyFunc == NULL)
		return NULL;

	if (freeFunc == NULL)
		return NULL;

	LinkedList list = (LinkedList)malloc(sizeof(struct LinkedList_s));

	if (list == NULL)
		return NULL;

	list->numberOfNodes = 0;
	list->copyFunc = copyFunc;
	list->freeFunc = freeFunc;
	list->equalFunc = equalFunc;
	list->printFunc = printFunc;
	list->head = NULL;
	list->tail = NULL;

	return list;

}

status destroyList(LinkedList list) {

	if (list == NULL)
		return failure;

	if (list->head == NULL) {
		free(list->tail);
		return success;
	}

	Node tmpNode = list->head;
	Node tmpNode2;

	while (tmpNode != NULL) {
		list->freeFunc(tmpNode->value);
		free(tmpNode->value);
		tmpNode = tmpNode->next;
	}

	tmpNode = list->head;
	list->head = list->head->next;
	tmpNode2 = tmpNode;

	while(tmpNode != NULL) {
		tmpNode = tmpNode->next;
		free(tmpNode2);
		tmpNode2 = tmpNode;
	}

	return success;

}

status appendNode(LinkedList list, Element elem) {

	if (list == NULL)
		return failure;

	if (elem == NULL)
		return failure;

	Node tmpNode = (Node)malloc(sizeof(struct Node_s));
	tmpNode->next = NULL;
	tmpNode->value = list->copyFunc(elem);
	Node tmpNode2;

	if (list->numberOfNodes == 0) {

		list->head = tmpNode;
		list->tail = tmpNode;

	}
	else
	{
		tmpNode2 = list->tail;
		tmpNode2->next = tmpNode;
		list->tail = tmpNode;

	}

	list->numberOfNodes++;

	return success;
}

status deleteNode(LinkedList list, Element elem) {

	if (list==NULL)
		return failure;

	if (elem==NULL)
		return failure;

	if (list->head == NULL)
		return failure;

	Node tmpNode = list->head;
	Node tmpNode2;

	if (list->equalFunc(tmpNode->value,elem)==true){

		list->head = tmpNode->next;

		list->freeFunc(tmpNode->value);
		free(tmpNode->value);
		free(tmpNode);

		list->numberOfNodes--;
		return success;

	} else {

		while (tmpNode->next!=NULL){

			if (list->equalFunc(tmpNode->next->value,elem)==true){

				if (list->equalFunc(tmpNode->next->value,list->tail->value)==true) {
					list->tail=tmpNode;

					list->freeFunc(tmpNode->next->value);
					free(tmpNode->next->value);
					free(tmpNode->next);

					tmpNode->next=NULL;


				}
				else
				{
					tmpNode2 = tmpNode->next->next;
					list->freeFunc(tmpNode->next->value);
					free(tmpNode->next->value);
					free(tmpNode->next);
					tmpNode->next = tmpNode2;

				}

				list->numberOfNodes--;
				return success;

			}

			tmpNode=tmpNode->next;

		}
	}

	return failure;


}

status displayList(LinkedList list) {

	if (list == NULL)
		return failure;

	if (list->head == NULL)
		return success;

	Node tmpNode = list->head;

	while (tmpNode != NULL){
		if (tmpNode->value != NULL)
			list->printFunc(tmpNode->value);
		tmpNode=tmpNode->next;
	}

	return success;

}

Element searchInList(LinkedList list, Element elem){

	if (list == NULL)
		return NULL;

	if (elem == NULL)
		return NULL;

	Element currElem ;
	Node tmpNode = list->head;

	while (tmpNode != NULL) {

		if (list->equalFunc(tmpNode->value,elem)==true) {
			currElem=tmpNode->value;
			return currElem;
		}

		tmpNode=tmpNode->next;
	}

	return NULL;

}
