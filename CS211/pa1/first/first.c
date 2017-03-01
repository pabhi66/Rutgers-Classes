/*
	This Program checks whether an integer is odd or even
	Abhishek Prajapati
	CS211 Assignment 1
*/

#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int main(int argc, char *argv[]){

	//Stores the user input
	long userInput;

	//Ask the user for an integer
	//printf("Please enter an integer: ");

	//store the user input
	//scanf("%d", &userInput);

	// if(scanf("%d", &userInput) != 1){
	// 	printf("error\n");
	// 	return 0;
	// }

	//check for valid entrie
	if(argv[1] == '\0'){
		printf("error\n");
		return 0;
	}

	//convert the (char) input to integer
	userInput = atoi(argv[1]);

	//Check and print the interger (even or odd)
	if(argc == 2){
		if(userInput % 2 == 0){
			printf("even\n");
		}
		else if(userInput % 2 != 0){
			printf("odd\n");
		}
	}else{
		printf("error\n");
	}
	return 0;

}