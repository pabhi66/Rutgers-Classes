// #include <stdio.h>
// #include <stdlib.h>
// #include <ctype.h>
// #include <string.h>
// #include <math.h>


// typedef struct Node{
// 	size_t tag;
// 	struct Node *next;
// 	struct Node *prev;
// 	int data;
// }Node;

// typedef struct Cache{
// 	int tag;
// 	int valid;
// 	struct node *head;
// }Cache;

// int cache_size = 0;
// int block_size = 0;
// int number_of_lines = 0;
// int numer_of_sets = 0;
// int memory_reads = 0;
// int memory_writes = 0;
// int cache_hits = 0;
// int cache_misses = 0;
// int tag = 0;
// FILE *file;
// char *n_way;
// char ip[40];
// char wr[2];
// char addrs[40];
// int i = 0;
// int block_lookup;
// int memory_address;
// char *binary;
// int assoc = 0;

// Node* linkedList(int assoc){
// 	Node *head = NULL;
// 	Node *list = NULL;
// 	int i = 0;
// 	for(; i<assoc; i++){
// 		head = (Node *)malloc(sizeof(Node));
// 		head->next = list;
// 		list = head;
// 	}
// 	head = list;
// 	return head;
// }

// Node* create_node(unsigned int num){
// 	Node* ptr;
// 	ptr = (Node*)malloc(sizeof(Node));
// 	if(ptr == NULL){
// 		return(struct Node*)NULL;
// 	}else{
// 		ptr->data = num;
// 		ptr->next = NULL;
// 		return ptr;
// 	}
// }

// int isPowerOfTwo (int size){
//  while (((size % 2) == 0) && size > 1) /* While x is even and > 1 */
//    size /= 2;
//    if(size == 1){
//    	return 1;
//    }
//  return 0;
// }

// int valid_c_size(char *size){
// 	if(isPowerOfTwo(size) == 1)
// 		return 1;
// 	return 0;
// }

// int valid_b_size(char *size){
// 	if(isPowerOfTwo(size) == 1){
// 		return 1;
// 	}
// 	return 0;
// }

// int valid_associative(char *assoc){
// 	if(strcmp(assoc, "direct") == 0){
// 		return 1;
// 	}
// 	else if(strcmp(assoc, "assoc") == 0){
// 		return 1;
// 	}
// 	else{
// 		char *n_way;
// 		n_way = strtok(assoc, ":");
//         n_way = strtok(NULL, ":");
//         int n = atoi(n_way);
//         if(isPowerOfTwo(n) == 1){
//         	return 1;
//         }
// 	}
// 	return 0;
// }


// void assign_cache(struct Cache *cache, int x){
// 	int i=0;
// 	for(; i<x; i++){
// 		cache[i].tag = 0;
// 		cache[i].valid = 0;
// 	}
// }

// void print(){
// 	printf("Memory reads: %d\n", memory_reads);
// 	printf("Memory writes: %d\n", memory_writes);
// 	printf("Cache hits: %d\n", cache_hits);
// 	printf("Cache misses: %d\n", cache_misses);
// }

// char *Hex_To_Binary(char address[]){
// 	char *binary = (char *)malloc(sizeof(char) * 100);
// 	int i = 0;
// 		for( i = 2 ; address[i] != '\0'; i++){
// 			switch(address[i]){
// 				case '0':  
//                 strcat(binary, "0000");  
//                 break;  
//             case '1':  
//                 strcat(binary, "0001");  
//                 break;  
//             case '2':  
//                 strcat(binary, "0010");  
//                 break;  
//             case '3':  
//                 strcat(binary, "0011");  
//                 break;  
//             case '4':  
//                 strcat(binary, "0100");  
//                 break;  
//             case '5':  
//                 strcat(binary, "0101");  
//                 break;  
//             case '6':  
//                 strcat(binary, "0110");  
//                 break;  
//             case '7':  
//                 strcat(binary, "0111");  
//                 break;  
//             case '8':  
//                 strcat(binary, "1000");  
//                 break;  
//             case '9':  
//                 strcat(binary, "1001");  
//                 break;  
//             case 'a':  
//             case 'A':  
//                 strcat(binary, "1010");  
//                 break;  
//             case 'b':  
//             case 'B':  
//                 strcat(binary, "1011");  
//                 break;  
//             case 'c':  
//             case 'C':  
//                 strcat(binary, "1100");  
//                 break;  
//             case 'd':  
//             case 'D':  
//                 strcat(binary, "1101");  
//                 break;  
//             case 'e':  
//             case 'E':  
//                 strcat(binary, "1110");  
//                 break;  
//             case 'f':  
//             case 'F':  
//                 strcat(binary, "1111");  
//                 break;
// 			}
// 		}
// 	return binary;
// }

// int ser(Cache *cache, int set_index, unsigned int tag){
// 	if(cache[set_index].valid == 0){
// 		return 0;
// 	}
// 	Node *ptr;
// 	ptr = cache[set_index].head;
// 	while(ptr!=NULL){
// 		if(ptr->data == tag){
// 			return 1;
// 		}
// 		ptr = ptr->next;
// 	}
// 	return 0;
// }


// int main(int argc, char **argv){

// 	if(argc > 5){
// 		exit(1);
// 	}

// 	cache_size = atoi(argv[1]);
// 	block_size = atoi(argv[3]);

// 	// if(valid_c_size(cache_size) == 0){
// 	// 	exit(1);
// 	// }
// 	// if(valid_b_size(block_size) == 0){
// 	// 	exit(1);
// 	// }

// 	// if(valid_associative(argv[2]) == 0){
// 	// 	exit(1);
// 	// }


// 	if(strcmp(argv[2], "direct") == 0){
// 		assoc = 1;
// 		number_of_lines = 1;
// 		numer_of_sets = cache_size/ (number_of_lines * block_size);
// 	}else if(strcmp(argv[2], "assoc") == 0){
// 		assoc = 2;
// 		numer_of_sets = 1;
// 		number_of_lines = cache_size / (numer_of_sets * block_size);
// 	}else{
// 		assoc = 3;
// 		n_way = strtok(argv[2], ":");
//         n_way = strtok(NULL, ":");
//         if(n_way != NULL)
//         number_of_lines = atoi(n_way);
//         numer_of_sets = cache_size/ (number_of_lines * block_size);
//         printf("%d\n", number_of_lines);
// 	}

// 	if(cache_size != numer_of_sets * number_of_lines * block_size){
// 			exit(1);
// 	}
	
// 	file = fopen(argv[4], "r");
// 	if(file == NULL){
// 		exit(1);
// 	}


// 	long begainning;
// 	begainning = ftell(file);
// 	fseek(file, 0, SEEK_END);
// 	if(ftell(file) == 0)
// 		return 0;
// 	fseek(file, begainning, SEEK_SET);
// 	struct Cache *cache = NULL;
// 	int index_offset;
// 	int block_offset;

// 	if(assoc == 1){
// 		index_offset = log2(numer_of_sets);
// 		block_offset = log2(block_size);
// 		cache = malloc(sizeof(struct Cache) * index_offset);
// 	}
// 	else if(assoc == 2){
// 		index_offset = log2(numer_of_sets);
// 		block_offset = log2(block_size);
// 		cache = (Cache*)calloc(numer_of_sets, sizeof(Cache));
// 		// int i = 0;
// 		// for(; i<numer_of_sets; i++){
// 		// 	cache[i].head = NULL;
// 		// 	cache[i].valid = 0;
// 		// }
// 		assign_cache(cache, numer_of_sets);
// 	}
// 	else{
// 		index_offset = log2(numer_of_sets);
// 		block_offset = log2(block_size);
// 		cache = (Cache*)calloc(numer_of_sets, sizeof(Cache));
// 		// int i = 0;
// 		// for(; i<numer_of_sets; i++){
// 		// 	cache[i].head = NULL;
// 		// 	cache[i].valid = 0;
// 		// }
// 		assign_cache(cache, numer_of_sets);
// 	}

// 	// int i;
// 	// for(i = 0; i<index_offset; i++){
// 	// 			cache[i].valid = 0;
// 	// 			cache[i].tag = 0;
// 	// 		}
// 	while(!feof(file)){
// 		fscanf(file, "%s %s %s", ip, wr, addrs);
// 		if(strcmp(ip, "#eof") == 0)
// 			break;
// 		//binary = Hex_To_Binary(xyz);
// 		if(strcmp(wr, "W") == 0){
// 			memory_writes++;
// 		}
// 		if(assoc == 1){
			
// 			memory_address = (int)strtol(addrs, NULL, 0);
// 			int m1 = (1 << block_offset) - 1;
// 			int b1 = memory_address & m1;
// 			memory_address >>= block_offset;
// 			int m2 = (1 << index_offset) - 1;
// 			int s1 = memory_address & m2;
// 			tag = memory_address >> index_offset;

// 			if(cache[s1].valid == 0){
// 				cache[s1].valid = 1;
// 				cache_misses++;
// 				memory_reads++;
// 			}else{
// 				if(cache[s1].tag == tag){
// 					cache_hits++;	
// 				}else{
// 					cache_misses++;
// 					memory_reads++;
// 				}
// 			}
// 			cache[s1].tag = tag;
// 		}

// 		else if(assoc == 2){
// 			binary = Hex_To_Binary(addrs);
// 			char *pointer;
// 			int index;
// 			pointer = (char*)malloc(index_offset+1);
// 			int i, count = 0;
// 			for(i = strlen(binary) - block_offset - index_offset; i<strlen(binary) - block_offset; i++){
// 				*(pointer+count) = binary[i];
// 				count++;
// 			}
// 			*(pointer+count) = '\0';
// 			//bin to dec
// 			int x,y=0,bin;
// 			unsigned int total =0;
			
// 			for(x = strlen(pointer)-1; x>=0; x--){
// 				bin = 0;
// 				if(pointer[x] == '1')
// 					bin = 1;

// 				total = total + (bin*(unsigned int)(pow(2,y)));
// 				y++;
// 			}

// 			index = total;

// 			char *po;
// 			unsigned int tag;
// 			po = (char*)malloc(strlen(binary) - block_offset - index_offset);
// 			int a;
// 			for(a = 0; a<strlen(binary) - block_offset - index_offset; a++){
// 				*(po + a) = binary[a];
// 			}
// 			*(po+a) = '\0';
// 			//bin to dec
// 			int xx, yy=0, binn;
// 			unsigned int totall = 0;
// 			for(xx = strlen(po)-1; xx>=0; xx--){
// 				binn = 0;
// 				if(po[xx] == '1')
// 					binn = 1;

// 				totall = totall + (binn*(unsigned int)(pow(2,yy)));
// 				yy++;
// 			}

// 			tag = totall;
// 			if(ser(cache, index, tag) == 1){
// 				cache_hits++;
// 				continue;
// 			}

// 			if(cache[index].valid < number_of_lines){
// 				cache_misses++;
// 				memory_reads++;
// 				Node *newNode = create_node(tag);
// 				newNode->next = cache[index].head;
// 				cache[index].head = newNode;
// 				cache[index].valid++;
// 				continue;
// 			}else{
// 				int i;
// 				cache_misses++;
// 				memory_reads++;

// 				Node* ptr;
// 				ptr = cache[index].head;
// 				for(i = 1;i<number_of_lines-1; i++){
// 					ptr = ptr->next;
// 				}
// 				ptr->next = NULL;
// 				Node *newNode = create_node(tag);
// 				newNode->next = cache[index].head;
// 				cache[index].head = newNode;
// 			}

// 		}

// 		else if(assoc == 3){
// 			binary = Hex_To_Binary(addrs);
// 			char *pointer;
// 			int index;
// 			pointer = (char*)malloc(index_offset+1);
// 			int i, count = 0;
// 			for(i = strlen(binary) - block_offset - index_offset; i<strlen(binary) - block_offset; i++){
// 				*(pointer+count) = binary[i];
// 				count++;
// 			}
// 			*(pointer+count) = '\0';
// 			//bin to dec
// 			int x,y=0,bin;
// 			unsigned int total =0;
			
// 			for(x = strlen(pointer)-1; x>=0; x--){
// 				bin = 0;
// 				if(pointer[x] == '1')
// 					bin = 1;

// 				total = total + (bin*(unsigned int)(pow(2,y)));
// 				y++;
// 			}

// 			index = total;

// 			char *po;
// 			unsigned int tag;
// 			po = (char*)malloc(strlen(binary) - block_offset - index_offset);
// 			int a;
// 			for(a = 0; a<strlen(binary) - block_offset - index_offset; a++){
// 				*(po + a) = binary[a];
// 			}
// 			*(po+a) = '\0';
// 			//bin to dec
// 			int xx, yy=0, binn;
// 			unsigned int totall = 0;
// 			for(xx = strlen(po)-1; xx>=0; xx--){
// 				binn = 0;
// 				if(po[xx] == '1')
// 					binn = 1;

// 				totall = totall + (binn*(unsigned int)(pow(2,yy)));
// 				yy++;
// 			}

// 			tag = totall;
// 			if(ser(cache, index, tag) == 1){
// 				cache_hits++;
// 				continue;
// 			}

// 			if(cache[index].valid < number_of_lines){
// 				cache_misses++;
// 				memory_reads++;
// 				Node *newNode = create_node(tag);
// 				newNode->next = cache[index].head;
// 				cache[index].head = newNode;
// 				cache[index].valid++;
// 				continue;
// 			}else{
// 				int i;
// 				cache_misses++;
// 				memory_reads++;

// 				Node* ptr;
// 				ptr = cache[index].head;
// 				for(i = 1;i<number_of_lines-1; i++){
// 					ptr = ptr->next;
// 				}
// 				ptr->next = NULL;
// 				Node *newNode = create_node(tag);
// 				newNode->next = cache[index].head;
// 				cache[index].head = newNode;
// 			}
// 		}
// 	}	
// 	print();
// 	return 0;
// }








//--------------------------------------------------------------------------------

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <math.h>


typedef struct Node{
	size_t tag;
	struct Node *next;
	struct Node *prev;
	int data;
}Node;

typedef struct Cache{
	int tag;
	int valid;
	//int set_index;
	//int data;
	struct node *head;
}Cache;

int cache_size = 0;
int block_size = 0;
int number_of_lines = 0;
int numer_of_sets = 0;
int memory_reads = 0;
int memory_writes = 0;
int cache_hits = 0;
int cache_misses = 0;
int tag = 0;
FILE *file;
char *n_way;
char ip[40];
char wr[2];
char addrs[40];
int i = 0;
int on;
int off;
char *pointer;
int block_lookup;
int memory_address;
char *binary;
int assoc = 0;

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

Node* create_node(unsigned int num){
	Node* ptr;
	ptr = (Node*)malloc(sizeof(Node));
	if(ptr == NULL){
		return(struct Node*)NULL;
	}else{
		ptr->data = num;
		ptr->next = NULL;
		return ptr;
	}
}

Node* fifo(Cache* cache, int index, unsigned int tag){
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

int isPowerOfTwo (int size){
 while (((size % 2) == 0) && size > 1) /* While x is even and > 1 */
   size /= 2;
   if(size == 1){
   	return 1;
   }
 return 0;
}

int valid_c_size(char *size){
	if(isPowerOfTwo(size) == 1)
		return 1;
	return 0;
}

int valid_b_size(char *size){
	if(isPowerOfTwo(size) == 1){
		return 1;
	}
	return 0;
}

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


void assign_cache(struct Cache *cache, int x){
	int i=0;
	for(; i<x; i++){
		cache[i].tag = 100;
		cache[i].valid = 100;
	}
}

void print(){
	printf("Memory reads: %d\n", memory_reads);
	printf("Memory writes: %d\n", memory_writes);
	printf("Cache hits: %d\n", cache_hits);
	printf("Cache misses: %d\n", cache_misses);
}

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

int ser(Cache *cache, int set_index, unsigned int tag){
	if(cache[set_index].valid == 0){
		return 0;
	}
	Node *ptr;
	ptr = cache[set_index].head;
	while(ptr!=NULL){
		if(ptr->data == tag){
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

	// if(valid_associative(argv[2]) == 0){
	// 	exit(1);
	// }

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
        	return 0;
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
	struct Cache *cache = NULL;
	int index_offset;
	int block_offset;

	if(assoc == 1){
		index_offset = log2(numer_of_sets);
		block_offset = log2(block_size);
		cache = malloc(sizeof(struct Cache) * index_offset);
		int i;
		for(i = 0; i<index_offset; i++){
			cache[i].valid = 0;
			cache[i].tag = 0;
		}
	}
	else if(assoc == 2){
		index_offset = log2(numer_of_sets);
		block_offset = log2(block_size);
		cache = (Cache*)calloc(numer_of_sets, sizeof(Cache));
		assign_cache(cache, numer_of_sets);
		int i = 0;
		for(; i<numer_of_sets; i++){
			cache[i].head = NULL;
			cache[i].valid = 0;
		}
	}
	else{
		index_offset = log2(numer_of_sets);
		block_offset = log2(block_size);
		cache = (Cache*)calloc(numer_of_sets, sizeof(Cache));
		assign_cache(cache, numer_of_sets);
		int i = 0;
		for(; i<numer_of_sets; i++){
			cache[i].head = NULL;
			cache[i].valid = 0;
		}
	}

	while(!feof(file)){
		fscanf(file, "%s %s %s", ip, wr, addrs);
		if(strcmp(ip, "#eof") == 0)
			break;
		//binary = Hex_To_Binary(xyz);
		if(strcmp(wr, "W") == 0){
			memory_writes++;
		}
		if(assoc == 1){
			
			memory_address = (int)strtol(addrs, NULL, 0);
			int m1 = (1 << block_offset) - 1;
			int b1 = memory_address & m1;
			memory_address >>= block_offset;
			int m2 = (1 << index_offset) - 1;
			int s1 = memory_address & m2;
			tag = memory_address >> index_offset;

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
			binary = Hex_To_Binary(addrs);
			off = strlen(binary) - block_offset - index_offset;
			on = strlen(binary) - block_offset;
			int index;
			pointer = (char*)malloc(index_offset+1);
			int i, count = 0;
			for(i = off; i<on; i++){
				*(pointer+count) = binary[i];
				count++;
			}
			*(pointer+count) = '\0';
			//bin to dec
			int x,y=0,bin;
			unsigned int decimal =0;
			
			for(x = strlen(pointer)-1; x>=0; x--){
				bin = 1;
				if(pointer[x] == '0')
					bin = 0;

				decimal += (bin*(pow(2,y)));
				y++;
			}

			char *po;
			unsigned int tag;
			po = (char*)malloc(off);
			int a;
			for(a = 0; a<off; a++){
				*(po + a) = binary[a];
			}
			*(po+a) = '\0';
			//bin to dec
			int xx, yy=0, binn;
			unsigned int decimall = 0;
			for(xx = strlen(po)-1; xx>=0; xx--){
				binn = 1;
				if(po[xx] == '0')
					binn = 0;

				decimall += (binn*(pow(2,yy)));
				yy++;
			}
			index = decimal;
			tag = decimall;

			// if(ser(cache,index,tag) == 0){
			// 	//Node *ass = linkedList(addrs);
			// 	char *biii = Hex_To_Binary(addrs);
			// }
			if(ser(cache, index, tag) == 1){
				cache_hits++;
				continue;
			}

			if(cache[index].valid < number_of_lines){
				cache_misses++;
				memory_reads++;
				// Node *newNode = create_node(tag);
				// newNode->next = cache[index].head;
				// cache[index].head = newNode;
				Node *newNode = fifo(cache, index, tag);
				cache[index].valid++;
				continue;
			}else{
				int i;
				cache_misses++;
				memory_reads++;

				Node* ptr;
				ptr = cache[index].head;
				for(i = 1;i<number_of_lines-1; i++){
					ptr = ptr->next;
				}
				ptr->next = NULL;
				// Node *newNode = create_node(tag);
				// newNode->next = cache[index].head;
				// cache[index].head = newNode;
				Node *newNode = fifo(cache,index,tag);
			}

		}
	}	
	print();
	return 0;
}
