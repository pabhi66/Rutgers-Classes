#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int *table;
int size = 1000;
int count0 = 0;

void Insert(int target){
	int hash = target % size;
	if(target == 0){
		count0++;
	}
	if(target < 0){
		hash = hash * -1;
	}
	if(table[hash] == '\0'){
		table[hash] = target;
		printf("inserted\n");
		return;
	}else if(table[hash] != '\0'){
		while(table[hash] != '\0'){
			if(table[hash] == target){
				printf("duplicate\n");
				return;
			}
			hash++;
			hash = hash % size;
		}
		table[hash] = target;
		printf("inserted\n");
		return;
	}
}

void Search(int target){

	if(target == 0){
		if(count0 > 0){
			printf("present\n");
			return;
		}
		else{
			printf("absent\n");
			return;
		}
	}
	// int hash = target % size;
	// int i=0;
	// int j = target % size-1;
	// for(i = 0; i<size; i++){
	// 	if(table[hash] == target){
	// 		printf("present\n");
	// 		return;
	// 	}
	// 	else if(hash == j+1){
	// 		printf("absent\n");
	// 		return;
	// 	}


	// 	 hash++;
	// 	hash = hash % size;
	// }

	int i = 0;
	for(i = 0; i < size; i++){
		if(table[i] == target){
			printf("present\n");
			return;
		}
	}
	printf("absent\n");
}

int main(int argc, char *argv[]){
	if(argv[1] == '\0'){
		printf("error\n");
		return 0;
	}

	FILE *file;
	table = (int*) malloc(size*sizeof(int));
	file = fopen(argv[1], "r");
	char singleLine[2000];

	if(file == NULL){
		printf("error\n");
		return 0;
	}
	// //======================ANOTHER WAY===========================
	// while(!feof(file)){
	// 	fgets(singleLine,2000,file);
	// 	if(singleLine[0] == 'i'){
	// 		int i = 2;
	// 		int pow = 1;
	// 		int x = atoi(&singleLine[2]);
	// 		for(i=3; i<2000; i++){
	// 			if(isdigit(atoi(&singleLine[i]))){
	// 				pow *= 10;
	// 				x = x * pow + atoi(&singleLine[i]);
	// 			}
	// 			else{
	// 				break;
	// 			}
	// 		}
	// 		Insert(x);
	// 	}else if(singleLine[0] == 's'){
	// 		int i = 2;
	// 		int pow = 1;
	// 		int x = atoi(&singleLine[2]);
	// 		for(i=3; i<2000; i++){
	// 			if(isdigit(atoi(&singleLine[i]))){
	// 				pow *= 10;
	// 				x = x * pow + atoi(&singleLine[i]);
	// 			}else{
	// 				break;
	// 			}
	// 		}
	// 		Search(x);
	// 	}else{
	// 		printf("error\n");
	// 	}
	// 	//puts(singleLine);
	// }
	// //===============================================================

	int lines = 0;
	char m = 0;
	
	int ccc = 0;

	while(!feof(file)){
		fscanf(file, "%c", &m);
		if(m == '\n')
			lines++;
	}
	//printf("%d\n", lines);
	rewind(file);

	int l = lines -1;


	char ch;
	int number;
	char trash;
	int count = 0;
	while(!feof(file)){
		if(ccc == l){
			exit(1);
		}
		fscanf(file, "%c", &ch);
		if(count == 0){
			if(ch == '\0'){
				printf("\n");
				exit(1);
			}
			count++;
		}
		fscanf(file, "%d", &number);
		fscanf(file, "%c", &trash);
		ccc++;
			//printf("%d\n", ccc);
		

		if(ch == 'i'){
			Insert(number);
		}else if(ch == 's'){
			Search(number);
		}else{
			printf("error\n");
		}
	}
	// int x = 0;
	// for(x = 0; x < 1000; x++){
	// 	if(table[x] == '\0'){
	// 		continue;
	// 	}else{
	// 		printf("%d\n", table[x]);
	// 	}
	// }
	fclose(file);
	return 0;
}
