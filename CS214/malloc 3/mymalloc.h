#ifndef MALLOC_H
#define MALLOC_H
#include <stdio.h>
#include <string.h>
#include <unistd.h>

static char myBlock[5000];

typedef struct MemEntry {
	struct MemEntry *prev;
	struct MemEntry *curr;
	int isfree; // 1=yes 0=no
	int size;
	char ptr[1];
	int ok;
}mementry;

mementry *ptr; //current pointer
mementry *previous; //previous pointer
mementry *next; //next pointer
mementry *ptr2;//pointer for smaller block of memory

//return pointer to head of allocated memory
void* my_malloc(unsigned int size,int line, char * file);

//frees a pointer if valid error other wise
void my_free(void *pointer,int line, char * file);

//prints out the errors
void errors(int i,int line, char * file);

#define malloc( x ) my_malloc( x,__LINE__ , __FILE__ )
#define free( x ) my_free( x, __LINE__ , __FILE__ )

#endif
