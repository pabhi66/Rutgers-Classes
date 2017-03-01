/*
	This program will read the file that has lines that starts with i and d.
	if the line starts with i then insert the following number to the linked list.
	if the line starts with d then delete the following number from the linked list.
	It should print error if file DNE or it contains lines with improper structures.
	Print blank line if the input file or linked list is empty.

	Abhishek Prajapati
	CS112 Assignment 1 
*/
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>



struct Node{
	int data;
	struct Node* next;
};

struct Node* head;

void Insert(int target){
	struct Node* temp = (struct Node*)malloc(sizeof(struct Node));
	temp->data = target;
	temp->next = NULL;

	if(head == NULL){
		head = temp;
		//free(temp);
		return;
	}

	struct Node* prev = NULL;
	struct Node* curr = head;
	while(curr != NULL){
		if(target == curr->data){
			return;
		}
		if(target < curr->data){
			if(curr == head){
				temp->next = curr;
				head = temp;
				//free(temp);
				return;
			}else{
				temp->next = curr;
				prev->next = temp;
				//free(temp);
				return;
			}
		}
		prev = curr;
		curr = curr->next;
		if(curr == NULL){
			prev->next = temp;
			temp->next = curr;
			return;
		}
	}
}


void Delete(int target){
	// if(head == NULL){
	// 	return;
	// }
	// struct Node* temp1 = head;
	// if(temp1->data == target){
	// 	head = temp1->next;
	// 	free(temp1);
	// 	return;
	// }
	// struct Node* prev = NULL;
	// struct Node* curr = head;
	// while(curr != NULL){
	// 	if(target == curr->data){
	// 		prev->next = curr->next;
	// 		//free(temp);
	// 	}else{
	// 		head = curr->next;
	// 	}
	// 	prev = curr;
	// 	curr = curr->next;
	// }
	// free(curr);

	struct Node *prev = NULL;
	struct Node *curr = head;

	if(curr == NULL){
		return;
	}
	while(curr != NULL){
		if(curr->data == target){
			if(prev != NULL){
				if(prev->data == curr->data){
					head = curr->next;
				}
				prev->next = curr->next;
			}else{
				head = curr->next;
			}
		}
		prev = curr;
		curr = curr->next;
	}
	free(curr);
}
void Print(){
	struct Node* temp = head;
	while(temp != NULL){
		printf("%d\t",temp->data);
		temp = temp->next;
	}
	free(temp);
	printf("\n");
}

// int main(int argc, char *argv[]){
// 	head = NULL; //list is empty

// 	//=================EXTRA STUFF======================
// 	// printf("How many numbers?\n");
// 	// int n,i,x;
// 	// scanf("%d", &n);
// 	// for(i=0; i<n; i++){
// 	// 	printf("Enter the number \n");
// 	// 	scanf("%d", &x);
// 	// 	Insert(x);
// 	// 	Print();
// 	// 	Delete(5);
// 	// }
// 	// Print();
// 	// return 0;
// 	//=================================================



// 	if(argv[1] == '\0'){
// 		printf("error\n");
// 		return 0;
// 	}

// 	FILE *file;
// 	file = fopen(argv[1], "r");
// 	char singleLine[2000];
// 	int i=0;
// 	char ch = '\0';

// 	if(file == NULL){
// 		printf("error\n");
// 		return 0;
// 	}

	
// 	while(!feof(file)){
// 		fgets(singleLine,2000,file);
// 		if(singleLine[0] == 'i'){

// 			//=================EXTRA STUFF======================
// 			//Insert(atoi(&singleLine[2]));
// 			// if(isdigit(atoi(&singleLine[3]))){
// 			// 	int x = atoi(&singleLine[2]) * 10 + atoi(&singleLine[3]);
// 			// 	Insert(x);
// 			// }else{
// 			// 	Insert(atoi(&singleLine[2]));
// 			// }
// 			//=================================================
// 			int i = 2;
// 			int pow = 1;
// 			int x = atoi(&singleLine[2]);
// 			for(i=3; i<2000; i++){
// 				if(isdigit(atoi(&singleLine[i]))){
// 					pow *= 10;
// 					x = x * pow + atoi(&singleLine[i]);
// 				}else{
// 					break;
// 				}
// 			}
// 			Insert(x);
// 		}
// 		else if(singleLine[0] == 'd'){
// 			//=================EXTRA STUFF======================
// 			//Delete(atoi(&singleLine[2]));
// 			//=================================================
// 			int i = 2;
// 			int pow = 1;
// 			int x = atoi(&singleLine[2]);
// 			for(i=3; i<2000; i++){
// 				if(isdigit(atoi(&singleLine[i]))){
// 					pow *= 10;
// 					x = x * pow + atoi(&singleLine[i]);
// 				}else{
// 					break;
// 				}
// 			}
// 			Delete(x);
// 		}
// 		//=================================================
// 		//puts(singleLine);
// 		//=================================================
// 	}
// 	Print();

// 	fclose(file);
// 	return 0;

// }

int main(int argc, char *argv[]){
	head = NULL;
	if(argv[1] == '\0'){
		printf("error\n");
		exit(0);
	}

	FILE *file;
	file = fopen(argv[1],"r");
	char ch;
	int number;
	char trash;

	if(file == NULL){
		printf("error\n");
		exit(0);
	}
	while(!feof(file)){
		fscanf(file, "%c", &ch);
		fscanf(file, "%d", &number);
		fscanf(file, "%c", &trash);

		if(ch == 'i'){
			Insert(number);
		}else if(ch == 'd'){
			Delete(number);
		}
		else if(ch == '\0'){
			printf("\n");
			exit(1);
		}
		else{
			printf("error\n");
			exit(1);
		}
	}
	Print();
	return 0;
}