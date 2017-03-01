=#include <stdio.h>
#include <stdlib.h>
#include <stdint.h>
#include <stddef.h>
#include <ctype.h>

/*Define node structuree*/
typedef struct node{
  int64_t data;
  struct node *next;
}hashTable;

hashTable* table[1000]; /*create a hash table with size 1000*/
int count = 0; /*unique address counter*/


/*create and return a new node and store the address(data) in the data*/
struct node *makeTable(int64_t address){
  hashTable *temp = malloc(sizeof(hashTable));
  temp->data = address;
  temp->next = NULL;
  return temp;
}

/*hash function*/
int64_t hashFunction(int64_t address){
  return address % 1000;
}

/* Inserts the address in to the hash table with chaining*/
void Insert(int64_t address){
  int64_t hashLocation = hashFunction(address); /*finds the key/location of the array*/

  hashTable *temp = makeTable(address); /*creates the new node*/

  if(table[hashLocation] == NULL){ /*check if the location is empty*/
    table[hashLocation] = temp; /*if yes then insert*/
    count++; /*increase unique counter*/
    return;
  }
  else if(table[hashLocation]->data == address){ /*else check if the address is already in the linked list*/
    return; /*if yes then return*/
  }else{
    hashTable *current = table[hashLocation]; /*else create a temperary iterator*/
    while(current->next != NULL){ /*loop through the end of the linked list*/
      current = current->next; 
      if(current->data == address){ /*check if the data already exists*/
	return; /*return if yes*/
      }
    }
    current->next = temp; /*add a linked list to the tail*/
    count++; /*increase unique counter*/
  }
}

int main(int argc, char *argv[]){
  FILE *file; /*file variable*/
  int64_t address; /*variable to store the address*/


  /*if no name/blank than print error*/
  if(argv[1] == '\0'){
    printf("error\n");
    return 0;
  }
  
  file = fopen(argv[1], "r"); /*open the file*/
  
  /*if file is empty return error*/
  if(file == NULL){
    printf("error\n");
    return 0;
  }

  /*if the file is empty then return 0*/
  long begainning;
  begainning = ftell(file);
  fseek(file, 0, SEEK_END);
  if (ftell(file) == 0)
    {
        return printf("%d\n",0);
    }
    fseek(file, begainning, SEEK_SET);
  
  /*loop through end of the file*/
  while(!feof(file)){
    fscanf(file, "%llx", &address); /*scan the line in the file*/
    Insert(address); /*insert the address in hash table*/
  }

  printf("%d\n", count); /*print the counter*/
  fclose(file); /*close the file*/

  return 0; /*end program*/
}
