/*
	This Program checks if the integer is a prime Number or not
	Abhishek Prajapati
	CS211 Assignment 1
*/

#include <stdio.h>
#include <math.h>
#include <ctype.h>
#include <stdlib.h>

int main(int argc, char *argv[]){

	//declar a variable for user input
	int userInput;

	//Ask the user for an integer
	//printf("Please enter an integer\n");

	//read the integer and store it
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

	//check if the integer is prime or not
	if(userInput == 2){
		printf("yes\n");
	}
	else if(userInput % 2 == 0){
		printf("no\n");
	}
	else if(userInput == 1){
		printf("no\n");
	}
	else{
		//double num = (double) sqrt((double) userInput);
		//int x = (int) ceil(num);
		int i=0;
		for(i=3; i<=userInput; i = i + 2){
			if(userInput % i == 0){
				if(userInput == i){
					printf("yes\n");
					break;
				}
				printf("no\n");
				break;
			}
		}

	}
	return 0;
}
