#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <math.h>

//create a node data tpe
//used to iterate fifo algorithm
typedef struct Node{
	size_t tag;
	struct Node *next;
	struct Node *prev;
	int data;
}Node;

//define a cache with tag valid and notvalid and head
typedef struct Cache{
	int tag;
	int valid;
	struct node *head;
}Cache;

//global varialbes 
int cache_size = 0; //cache size
int block_size = 0;	//block size
int number_of_lines = 0; //set size or number of lines in a set
int numer_of_sets = 0; //number of sets in a cache
int memory_reads = 0; //memory read counter
int memory_writes = 0; //memory write counter
int cache_hits = 0; //cache hits counter
int cache_misses = 0; //cache misses counter
unsigned int tag = 0; //hold tag size
FILE *file; //file variable
Cache *cache = NULL; //create a empty cache
int index_offset; //index offset of the cache
int block_offset; //block offset of the cache
char *n_way; //for assoc:n associativity
char ip[40]; //strores instruction pointer
char wr[2]; //stores read or write 
char addrs[40]; //reads in address
int i = 0; //index counter
int on; //set length
int off; //tag length
char *pointer; //used to iterate cache in assoc and assoc:n
int block_lookup; //strores block value
int memory_address; //stroes memory address
char *binary; //stores binary representation of hex
int assoc = 0; //associaviaty identifier




//create a empty linked list and assign for fifo
Node* linkedList(int assoc){
	Node *head = NULL;
	Node *list = NULL;
	int i = 0;
	for(; i<assoc; i++){
		head = (Node *)malloc(sizeof(Node));
		head->next = list;
		list = head;
	}
	head = list;
	return head;
}

//run fifo replacement algorithm
Node* fifo(int index, int j, int kk){
	if(j == 1){
		cache[index].valid++;
	}
	if(kk == 1){
		cache_misses++;
		memory_reads++;
	}
	Node *bhos;
	bhos = (Node*)malloc(sizeof(Node));
	if(bhos != NULL){
		bhos->data = tag;
		bhos->next = NULL;
		bhos->next = cache[index].head;
		cache[index].head = bhos;
		return bhos;
	}else{
		bhos->next = cache[index].head;
		cache[index].head = bhos;
		return bhos;
	}
}

//check if the value is power of two
int isPowerOfTwo (int size){
 while (((size % 2) == 0) && size > 1) /* While x is even and > 1 */
   size /= 2;
   if(size == 1){
   	return 1;
   }
 return 0;
}

//check for valid cache size
int valid_c_size(char *size){
	if(isPowerOfTwo(size) == 1)
		return 1;
	return 0;
}

//check for valid block size
int valid_b_size(char *size){
	if(isPowerOfTwo(size) == 1){
		return 1;
	}
	return 0;
}

//check for valid associative
int valid_associative(char *assoc){
	if(strcmp(assoc, "direct") == 0){
		return 1;
	}
	else if(strcmp(assoc, "assoc") == 0){
		return 1;
	}
	else{
		char *n_way;
		n_way = strtok(assoc, ":");
        n_way = strtok(NULL, ":");
        int n = atoi(n_way);
        if(isPowerOfTwo(n) == 1){
        	return 1;
        }
	}
	return 0;
}

//asign cache to NULL
void assign_cache(int x){
	int i=0;
	for(; i<x; i++){
		cache[i].tag = 0;
		cache[i].valid = 0;
	}
}

//print all the counters
void print(){
	printf("Memory reads: %d\n", memory_reads);
	printf("Memory writes: %d\n", memory_writes);
	printf("Cache hits: %d\n", cache_hits);
	printf("Cache misses: %d\n", cache_misses);
}

//convert hex to binary
char *Hex_To_Binary(char address[]){
	char *binary = (char *)malloc(sizeof(char) * 100);
	int i = 0;
		for( i = 2 ; address[i] != '\0'; i++){
			switch(address[i]){
				case '0':  
                strcat(binary, "0000");  
                break;  
            case '1':  
                strcat(binary, "0001");  
                break;  
            case '2':  
                strcat(binary, "0010");  
                break;  
            case '3':  
                strcat(binary, "0011");  
                break;  
            case '4':  
                strcat(binary, "0100");  
                break;  
            case '5':  
                strcat(binary, "0101");  
                break;  
            case '6':  
                strcat(binary, "0110");  
                break;  
            case '7':  
                strcat(binary, "0111");  
                break;  
            case '8':  
                strcat(binary, "1000");  
                break;  
            case '9':  
                strcat(binary, "1001");  
                break;  
            case 'a':  
            case 'A':  
                strcat(binary, "1010");  
                break;  
            case 'b':  
            case 'B':  
                strcat(binary, "1011");  
                break;  
            case 'c':  
            case 'C':  
                strcat(binary, "1100");  
                break;  
            case 'd':  
            case 'D':  
                strcat(binary, "1101");  
                break;  
            case 'e':  
            case 'E':  
                strcat(binary, "1110");  
                break;  
            case 'f':  
            case 'F':  
                strcat(binary, "1111");  
                break;
			}
		}
	return binary;
}

//get tag length
int get_tag_length(char address[], int index_offset, int block_offset){
    return strlen(address) - index_offset - block_offset;
}

//get set length
int get_length(char address[], int block_offset){
	return strlen(address) - block_offset;
}

//search for hit in the cache
int isHit(int index){
	if(cache[index].valid == 0){
		return 0;
	}
	Node *ptr;
	ptr = cache[index].head;
	while(ptr!=NULL){
		if(ptr->data == tag){
			cache_hits++;
			return 1;
		}
		ptr = ptr->next;
	}
	return 0;
}


int main(int argc, char **argv){

	if(argc > 5){
		exit(1);
	}

	cache_size = atoi(argv[1]);
	block_size = atoi(argv[3]);

	if(valid_c_size(cache_size) == 0){
		exit(1);
	}
	if(valid_b_size(block_size) == 0){
		exit(1);
	}

	if(strcmp(argv[2], "direct") == 0){
		assoc = 1;
		number_of_lines = 1;
		numer_of_sets = cache_size/ (number_of_lines * block_size);
	}else if(strcmp(argv[2], "assoc") == 0){
		assoc = 2;
		numer_of_sets = 1;
		number_of_lines = cache_size / (numer_of_sets * block_size);
	}else{
		assoc = 3;
		n_way = strtok(argv[2], ":");
        n_way = strtok(NULL, ":");
        number_of_lines = atoi(n_way);
        if(isPowerOfTwo(number_of_lines) == 0){
        	exit(1);
        }
        numer_of_sets = cache_size/ (number_of_lines * block_size);
	}
	if(cache_size != numer_of_sets * number_of_lines * block_size){
			exit(1);
	}
	
	file = fopen(argv[4], "r");
	if(file == NULL){
		exit(1);
	}


	long begainning;
	begainning = ftell(file);
	fseek(file, 0, SEEK_END);
	if(ftell(file) == 0)
		return 0;
	fseek(file, begainning, SEEK_SET);

	if(assoc == 1){
		index_offset = log2(numer_of_sets);
		block_offset = log2(block_size);
		cache = malloc(sizeof(Cache) * index_offset);
		assign_cache(numer_of_sets);
	}
	else if(assoc == 2 || assoc == 3){
		index_offset = log2(numer_of_sets);
		block_offset = log2(block_size);
		cache = (Cache*)malloc(sizeof(Cache) * numer_of_sets);
		assign_cache(numer_of_sets);
	}

	while(!feof(file)){
		fscanf(file, "%s %s %s", ip, wr, addrs);
		if(strcmp(ip, "#eof") == 0)
			break;
		if(strcmp(wr, "W") == 0){
			memory_writes++;
		}

		binary = Hex_To_Binary(addrs);
		off = get_tag_length(binary, index_offset, block_offset);
		on = get_length(binary,block_offset);


		if(assoc == 1){
			
			memory_address = (int)strtol(addrs, NULL, 0);
			int b1 = memory_address & on;
			memory_address >>= block_offset;
			int m2 = (1 << index_offset) - 1;
			int s1 = memory_address & m2;
			tag = (int)strtol(addrs, NULL, 0);
			if(cache[s1].valid == 0){
				cache[s1].valid = 1;
				cache_misses++;
				memory_reads++;
			}else{
				if(cache[s1].tag == tag){
					cache_hits++;	
				}else{
					cache_misses++;
					memory_reads++;
				}
			}
			cache[s1].tag = tag;
		}

		else if(assoc == 2 || assoc == 3){
			memory_address = (int)strtol(addrs, NULL, 0);
			int b1 = memory_address & on; //block index
			memory_address >>= block_offset;
			int m2 = (1 << index_offset) - 1;
			int s1 = memory_address & m2; //set index
			tag = (int)strtol(addrs, NULL, 0);
			if(isHit(s1) == 1) continue;

			else if(cache[s1].valid >= number_of_lines){
				int i=1;
				int j = 0;
				int kk = 1;

				Node* ptr = cache[s1].head;
				for(;i<number_of_lines-1; i++){
					ptr = ptr->next;
				}
				ptr->next = NULL;
				Node *newNode = fifo(s1, j, kk);
			}else{
				int j = 1;
				int kk = 1;
				Node *newNode = fifo(s1, j, kk);
			}

		}
	}	
	print();
	return 0;
}
