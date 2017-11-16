
#include <stdio.h>
#include <string.h>
#include "sorted-list.h"

//*************** COMPARE METHODS TO COMPARE TWO OBJECTS *************************//
int compareStrings(void *obj1, void *obj2){
    return strcmp(obj1, obj2);
}

int compareIntegers(void *obj1, void *obj2){
    return *(int*)obj1 - *(int*)obj2;
}

int compareDoubles(void *obj1, void *obj2){
    return (*(double*)obj1 < *(double*)obj2) ?  -1 : ((*(double*)obj1 > *(double*)obj2) ? 1 : 0);
}

int compareCharacters(void* obj1, void * obj2){
    char a = *(char*)obj1;
    char b = *(char*)obj2;
    return a-b;
}

// int compareLong(void* obj1, void* obj2){
//  return *(long*)obj1 - *(long*)obj2;
// }

int compareFloats(void* obj1, void* obj2){
    return (*(float*)obj1 < *(float*)obj2) ?  -1 : ((*(float*)obj1 > *(float*)obj2) ? 1 : 0);
}

//*******************************************************************************

//**************** DESTROY MEHOTDS TO DESTROY CURRENT OBJECT **********************//

void destroy(void *obj) {
    if(!obj)
        free(obj);
    return;
}

void intDestroy(void* obj){
    if(!obj)
        free((int*)obj);
    return;
}

void charDestroy(void* obj){
    if(!obj)
        free((char*)obj);
    return;
}

void doubleDestroy(void* obj){
    if(!obj)    
        free((double*)obj);
    return;
}

void longDestroy(void* obj){
    if(!obj)
        free((long*)obj);
    return;
}

void floatDestroy(void* obj){
    if(!obj)
        free((float*)obj);
    return;
}
//***********************************************************************
//************************** PRINT ALL DATA ******************************
/**
    FOR i;
    1 = int, 2 = double, 3 = char, 4 = string, 5 = long, 6 = float
*/
   
void printIter(SortedListIteratorPtr ptr ,int i){
    void *data;
    do{
        data = SLNextItem(ptr);
        if(data == NULL) continue;
        if(i == 1) {
            printf("%d --> ", *(int*)data);
        }
        else if(i == 2) {
            printf("%lf --> ", *(double*)data);
        }
        else if(i==3) {
            printf("%c --> ", *(char*)data);
        }
        else if(i==4) {
            printf("%s --> ", (char*)data);
        }
        else if(i == 5){
            printf("%ld --> ", *(long*)data);
        }
        else if(i == 6){
            printf("%f --> ", *(double*)data);
        }

    }while(data != NULL);

    printf("null\n");
}

void printList(SortedListPtr ptr ,int i){
   struct Node* temp = ptr->head;
    while(temp != NULL){
        if(i == 1) {
            printf("%d --> ", *(int*)temp->data);
        }
        else if(i == 2) {
            printf("%lf --> ", *(double*)temp->data);
        }
        else if(i==3) {
            printf("%c --> ", *(char*)temp->data);
        }
        else if(i==4) {
            printf("%s --> ", (char*)temp->data);
        }
        else if(i == 5){
            printf("%ld --> ", *(long*)temp->data);
        }
        else if(i == 6){
            printf("%f --> ", *(double*)temp->data);
        }
        temp = temp->next;
    }

    printf("null\n");
}

//**************************** MAIN ***************************************


int main(int argc, char *argv[])
{

    /*
        MAIN CREATES A SORTED LISTPOINTERS OF DIFFERENT DATA TYPES, AND THEN
        INSERTS AND DELEATES THE DATA USING "SLCREATE" AND "SLREMOVE". 
        IT USES APPROPRIATE COMPARE AND DESTROY METHODS CREATED ABOVE
        TO COMPARE AND DESTRUCT VARIOUS DATA TYPE. AND IT PRINTS THE LIST
        AFTER INSERTING AND REMOVING DATA TO SORTED LIST.

        THE FOLLOING TESTS FOR INTEGERS, DOUBLES/FLOATS, CHARACTERS, AND STRINGS
    */


    //Testing Integers
    SortedListPtr integers = SLCreate(compareIntegers, intDestroy);
    int a = 1, b = 999, c = 8, d = 3, e = 5, f = 5, g = 7, h = 100, i = 1000, j = 9999;
    printf("Testing Integers:\n");
    SLInsert(integers, (void*)&a);
    SLInsert(integers, (void*)&b);
    SLInsert(integers, (void*)&c);
    SLInsert(integers, (void*)&d);
    SLInsert(integers, (void*)&e);
    SLInsert(integers, (void*)&f);
    SLInsert(integers, (void*)&g);
    SLInsert(integers, (void*)&h);
    SLInsert(integers, (void*)&i);
    SLInsert(integers, (void*)&j);
    SLRemove(integers, (void*)&c);
    SLRemove(integers, (void*)&j);
    printList(integers, 1);
    SortedListIteratorPtr itr1 = SLCreateIterator(integers);
    printIter(itr1, 1);

    //Testing Doubles
    SortedListPtr doubles = SLCreate(compareDoubles, doubleDestroy);
    double aa = 0.3, bb = 009.54, cc = 100.4, dd = 3.14159, ee = 432432424.32424234;
    printf("\nTesting Doubles:\n");
    SLInsert(doubles, (void*)&aa);
    SLInsert(doubles, (void*)&bb);
    SLInsert(doubles, (void*)&cc);
    SLInsert(doubles, (void*)&dd);
    SLInsert(doubles, (void*)&ee);
    SLRemove(doubles, (void*)&cc);
    SLRemove(doubles, (void*)&aa);
    printList(doubles, 2);
    SortedListIteratorPtr itr2 = SLCreateIterator(doubles);
    printIter(itr2, 2);

    //Testing Floats
    SortedListPtr floats = SLCreate(compareFloats, floatDestroy);
    double aaz = 0.3, bbz = 009.54, ccz = 100.4, ddz= 3.14159, eez = 432432424.32424234;
    printf("\nTesting Floats:\n");
    SLInsert(floats, (void*)&aaz);
    SLInsert(floats, (void*)&bbz);
    SLInsert(floats, (void*)&ccz);
    SLInsert(floats, (void*)&ddz);
    SLInsert(floats, (void*)&eez);
    SLRemove(floats, (void*)&ccz);
    SLRemove(floats, (void*)&aaz);
    printList(floats, 6);

    SortedListIteratorPtr itr5 = SLCreateIterator(floats);
    printIter(itr5, 6);

    //Testing Chars
    SortedListPtr characters = SLCreate(compareCharacters, charDestroy);
    char aaa = 's', bbb = 'a', ccc = 'c', ddd = 'z', eee = 'a', fff = 'j';
    printf("\nTesting Characters:\n");
    SLInsert(characters, (void*)&aaa);
    SLInsert(characters, (void*)&bbb);
    SLInsert(characters, (void*)&ccc);
    SLInsert(characters, (void*)&ddd);
    SLInsert(characters, (void*)&eee);
    SLInsert(characters, (void*)&fff);
    SLRemove(characters, (void*)&ccc);
    printList(characters, 3);

    SortedListIteratorPtr itr3 = SLCreateIterator(characters);
    printIter(itr3, 3);

    //Testing longs
    SortedListPtr strings = SLCreate(compareStrings, destroy);
    char *word1 = "hello";
    char *word2 = "world";
    char *word3 = "abhi";
    char *word4 = "hello";
    char *word5 = "eric";
    char *word6 = "you";
    printf("\nTesting Strings:\n");
    SLInsert(strings, (void*)word1);
    SLInsert(strings, (void*)word2);
    SLInsert(strings, (void*)word3);
    SLInsert(strings, (void*)word4);
    SLInsert(strings, (void*)word5);
    SLInsert(strings, (void*)word6);
    SLRemove(strings, (void*)word2);
    printList(strings, 4);

    SortedListIteratorPtr itr4 = SLCreateIterator(strings);
    printIter(itr4, 4);


    //DESTROY EVERYTHING
    SLDestroy(doubles);
    SLDestroy(characters);
    SLDestroy(floats);
    SLDestroy(strings);
    SLDestroy(integers);
    SLDestroyIterator(itr1);
    SLDestroyIterator(itr2);
    SLDestroyIterator(itr3);
    SLDestroyIterator(itr4);

    return 0;
}
