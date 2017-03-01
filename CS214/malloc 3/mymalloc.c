#include <stdio.h>
#include "mymalloc.h"

//pointing to beginning to my array of memory
static mementry *head=(mementry*)myBlock;

//allocate memory if applicable and return pointer
void* my_malloc(unsigned int size,int line, char * file) {
    static int init = 0;
    if(size>0) { //precede only if size > 0
        // initialize the memory when it is allocated the first time
        if(init == 0){
            // create a head of the memory chunk of block
            head->prev = head->curr = 0;
            head->size = 5000 - sizeof(mementry)-2000;
            head->isfree = 1;
            head->ok = 1;
            head->ptr[0] = 'a';
            init = 1;
        }
        ptr = head; //points to the head of memory block

        do{
            if(!ptr->isfree) {
                //current memory is used go to next one
                ptr = ptr->curr;
            }
            else if(ptr->size < size) {
                //current block is smaller go to next block
                ptr = ptr->curr;
            }
            else if(ptr->size < (size + sizeof(mementry))) {
                // this block of memory is available but there's not enough memory to hold the HEADER of the next chunk
                ptr->isfree = 0;
                printf(" Allocated memory:\t%p && size == %d\n", ptr, ptr->size);
                return (char*)ptr + sizeof(mementry);
            }
            else {
                //create the head of the next memory with needed space
                next = (mementry*)((char*)ptr + sizeof(mementry) + size);
                //manipualiting pointers
                next->prev = ptr;
                next->isfree=1;
                next->ok = 1;
                next->ptr[0] = 'a';
                next->curr = ptr->curr;
                if(ptr->curr != 0) { ptr->curr->prev = next; }
                ptr->curr = next;
                next->size = (int) (ptr->size - sizeof(mementry) - size);
                ptr->size = size;
                ptr->isfree = 0;
                printf(" Allocated memory:\t%p && size = %d\n", ptr, ptr->size);
                return (char*)ptr + sizeof(mementry);
            }
        } while(ptr != 0);
        errors(1,line, file);
    }
    else{
        errors(2,line,file);
    }
    return 0;
}


//free pointers if applicable otherwise print an error using build in macros
void my_free(void *pointer,int line, char * file) {
    //checks whether the pointer is allocated
    if(pointer!=0) {
        /*if the pointer is in the valid range*/
        if(pointer>(void *)head && pointer<((void *)head+5000)){
            ptr = (mementry*)((char*)pointer - sizeof(mementry)); //assign pointer
            //check if pointer has allocated memory
            if(ptr->ptr[0]=='a'){
                //printing erros
                //error when trying to free same pointer twice
                if(ptr->isfree != 0){
                    errors(3,line,file);
                }
                else{
                    //if the block is not free
                    if((previous = ptr->prev) != 0 && previous->isfree==1){
                        //previous block is free to merge this block with previous
                        previous->size += sizeof(mementry) + ptr->size;
                        ptr->isfree=1;
                        previous->curr = ptr->curr;
                        if(ptr->curr != 0){
                            ptr->curr->prev = previous;
                        }
                    }
                    else{
                        ptr->isfree = 1;
                        previous = ptr;
                    }

                    if((next = ptr->curr) != 0 && next->isfree) {
                        // the next chunk is free, merge with it
                        previous->size += sizeof(mementry) + next->size;
                        previous->isfree = 1;
                        previous->curr = next->curr;
                        if(next->curr != 0){
                            next->curr->prev=previous;
                        }
                    }
                }
            }
                //error when trying to free not allocated pointers
            else { errors(4,line, file); }
        }
            //errors when trying to free
        else { errors(5,line, file);}
    }
        //error when freeing not allocated memory
    else { errors(6,line, file); }
}

//prints error
void errors(int i,int line, char * file){
    switch(i){
        case 1:
            printf("AHAH! ERROR: can't allocate anymore memory from block @ line: %d file: %s.\n",line,file);
            break;
        case 2:
            printf("AHAH! ERROR: size has to be > 0 @ line: %d file: %s.\n",line,file);
            break;
        case 3:
            printf("AHAH! ERROR: freeing same pointer twice @ line: %d file: %s.\n",line,file);
            break;
        case 4:
            printf("AHAH! ERROR: freeing pointers that were not allocated @ line: %d file: %s.\n",line,file);
            break;
        case 5:
            printf("AHAH! ERROR: pointer not allocated OR pointer go out of memory range @ line: %d file: %s.\n",line,file);
            break;
        case 6:
            printf("AHAH! ERROR: freeing not created memory. @ line: %d file: %s.\n",line,file);
            break;
    }
}
