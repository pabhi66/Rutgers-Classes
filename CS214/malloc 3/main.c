#include <stdio.h>
#include "mymalloc.h"

int main()
{
    //TEST 1
    printf("Test1: trying to free non malloced pointer\n");
    int x=20;
    free(&x);
    printf("\n");

    //TEST 2
    printf("Test 2: allocating -> freing -> allocating again -> freeing again -> no error\n");
    char * ptr11=(char *)malloc(250);
    free(ptr11);
    ptr11=(char *)malloc(250);
    free(ptr11);
    printf("\n");

    //TEST 3
    printf("Test 3: allocate more memoty then block size\n");
    int * ptr=(int *)malloc(sizeof(int)*900);
    free(ptr);
    printf("\n");

    //TEST 4
    printf("Test 4: free()ing pointers to dynamic memory that were not returned from malloc()\n");
    char *ptr2=(char *)malloc(380);
    free(ptr2+300);
    free(ptr2);
    printf("\n");

    //TEST 5
    printf("Test 5: trying to free same pointer two times\n");
    char * ptr22=(char *)malloc(380);
    free(ptr22);
    free(ptr22);
    printf("\n");

    //TEST 6
    printf("Test 6: creating memory with size 0 and try to free it\n");
    char * ptrr=(char *)malloc(0);
    free(ptrr);
    printf("\n");

    return 0;
}
