#include <stdlib.h>
#include "sorted-list.h"
#include <string.h>
#include <stdio.h>

/**
 * initialize and allocate memory for sorted list
 */
SortedListPtr SLCreate(CompareFuncT cf, DestructFuncT df){
    if(cf == NULL || df == NULL) return NULL;

    //allocate memory for the sorted list
    SortedListPtr sortedPointer = malloc(sizeof(struct SortedList));

    //check if malloc returned null
    if(sortedPointer == NULL){
        free(sortedPointer);
        return NULL;
    }

    //initialize the sorted list to default
    sortedPointer->head = NULL;
    sortedPointer->size = 0;
    sortedPointer->compare = cf;
    sortedPointer->destroy = df;

    return sortedPointer; //return the sorted Pointer
}

//================================================================================================================

/**
 * go through the entire sorted list and free every elements and free the list
 */
void SLDestroy(SortedListPtr list){
    //store the list head to temp variable
    if(list == NULL) return;
    DestructFuncT destroy = list->destroy;
    struct Node* temp = list->head, *next = NULL;

    //look through the entire list and free up memory for each nodes in the list
    while(temp != NULL){
        next = temp->next;
        destroy(temp->data);
        free(temp);
        temp = next;
    }

    //finally free the last node and the list its self
    free(temp);
    free(list);
}

//================================================================================================================

/**
 * Insert an item/new object into a sorted list
 */

int SLInsert(SortedListPtr list, void *newObj) {

    //if empty list or new object os null return
    if(list == NULL || newObj == NULL)
        return 0;

    //temperary variables to iterate the Sorted List
    struct Node* current = list->head, *prev = NULL;

    //Loop to check if newObj is alrady in the sorted list
    while(current != NULL){
        if(current->data == newObj) return 0;
        current = current->next;
    }

    current = list->head; //assigne current to head

    //if list is empty create a new node and make that the head and return
    if (current == NULL) {
        struct Node* newNode = malloc(sizeof(struct Node));
        newNode->data = newObj;
        list->head = newNode;
        newNode->next = NULL;
        list->size++;
        return 1;
    }

        //if the newObj already exists then return 0
    else if(list->compare(current->data,newObj) == 0) return 0;

        //if newObj is > then the head then insert the new object to head and return
    else if(list->compare(newObj, current->data) > 0){
        struct Node* newNode = malloc(sizeof(struct Node));
        newNode->data = newObj;
        newNode->next = list->head;
        list->head = newNode;
        list->size++;
        return 1;
    }

        //else it must be greater than the head, so iterate through the sorted list and find the right spot to insert newObj
    else {
        struct Node* newNode = malloc(sizeof(struct Node));
        newNode->data = newObj;

        //loop to iterate sorted list
        for(current = current->next, prev = list->head ; ; prev = current, current = current->next ){
            //if newObj is smaller than the tail then make newObj the tail and return
            if(current == NULL){
                prev->next = newNode;
                list->size++;
                return 1;
            }
                //else if newObj exists in sorted list return
            else if(list->compare(current->data, newObj) == 0) return 0;
                //else insert the newObj in the right place and return
            else if(list->compare(newObj, current->data) > 0){
                newNode->next = current;
                prev->next = newNode;
                list->size++;
                return 1;
            }
        }
    }
}

//================================================================================================================

/**
 * Remove an item/new object from a sorted list
 */
int SLRemove(SortedListPtr list, void *newObj){
    //temperary variables to iterate the sorted list
    struct Node* current = list->head;
    struct Node* prev = NULL;

    //if the sorted list is empty return
    if(list == NULL || current == NULL) return 0;

    //loop until you find the object to be removed
    while(list->compare(current->data, newObj) != 0 && current->next != NULL){
        prev = current;
        current = current->next;
    }

    //if its the first object then make head = to the second item in sorted list
    if(prev == NULL){
        list->head = current->next;
        list->destroy(current);
        list->size--;
        return  1;
    }
    //else remove the object and free memory
    prev->next = current->next;
    list->destroy(current);
    list->size--;
    return 1;
}
//================================================================================================================

/**
 * initialize and allocate memory for sortedListIterator
 */
SortedListIteratorPtr SLCreateIterator(SortedListPtr list){
    if(list == NULL) return NULL;

    //allocate memory
    SortedListIteratorPtr iteratorPtr = malloc(sizeof(struct SortedListIterator));

    //check to see if malloc return NULL
    if(iteratorPtr == NULL){
        free(iteratorPtr);
        return NULL;
    }
    //initialize
    iteratorPtr->pointer = list->head;
    iteratorPtr->counter = list->size;
    iteratorPtr->tracker = 0;
    return iteratorPtr;
}

//================================================================================================================

/**
 * loop through the Sorted list iterator and free all the elements including SortedlistIterator pointer
 */
void SLDestroyIterator(SortedListIteratorPtr iter){
    if(iter == NULL) return;
    free(iter); //free iter pointer
}

//================================================================================================================
/*
 * SLNextItem returns a pointer to the data associated with the
 *  next item in the SortedListIterator's list
// */
void * SLNextItem(SortedListIteratorPtr iter){

    if(iter == NULL) return NULL;

    if(iter->tracker == iter->counter) return iter->pointer; //if null return null

    iter->tracker++; //increment the tracer
    void *data = iter->pointer->data; //store the data to be returned
    iter->pointer = iter->pointer->next; //make it point to next item
    return data; //return data

}

//================================================================================================================
/*
 * SLGetItem should return a pointer to the current data item
 */
void * SLGetItem( SortedListIteratorPtr iter ){

    if(iter == NULL) return NULL;

    if(iter->tracker > iter->counter) //if null return null
        return NULL;
    return iter->pointer->data; //return current tracker/pointer data
}

