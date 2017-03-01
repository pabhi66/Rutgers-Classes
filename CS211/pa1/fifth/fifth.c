#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int main(int argc, char *argv[]){
	if(argv[1] == '\0'){
		printf("error\n");
		return 0;
	}

	FILE *file;
	file = fopen(argv[1], "r");
	char singleLine[2000];
	//int i=0;
	char ch = '\0';

	if(file == NULL){
		printf("error\n");
		return 0;
	}



	fgets(singleLine,2000,file);
	if(singleLine[0] == '\0'){
		return 0;
	}
	int i=0;
	int j=0;
	int x = atoi(&singleLine[0]);
	int pow = 1;
	while(singleLine[j] != '\t'){
		for(i=1; i<2000; i++){
				if(isdigit(atoi(&singleLine[i]))){
					if(singleLine[i] == '\t')
						break;
					pow *= 10;
					x = x * pow + atoi(&singleLine[i]);
				}
			}
			j++;
	}

	int y = atoi(&singleLine[j+1]);
	pow = 1;
	while(singleLine[j] != '\0'){
		for(i=j; i<2000; i++){
			if(isdigit(atoi(&singleLine[i]))){
				if(singleLine[i] == '\0')
					break;
				pow *= 10;
				y = y * pow + atoi(&singleLine[i]);
			}
		}
		j++;
	}


	//printf("%d\n",x);
	//printf("%d\n", y);
	//printf("\n");
	//puts(singleLine);

	int matrix1[x][y];
	int matrix2[x][y];

	if(x == 0 || y == 0){
		printf("error\n");
		exit(1);
	}

	int mat1[x*y];
	int mat2[x*y];
	int count = 0;
	int k = 0;
	int l = 0;
	int addition[x*y];
///////////////////////////////////////////////////

	int c = 0;

	//fscanf(file, "%d", &i);

	while(!feof(file)){
		//fgets(singleLine,200,file);
		//puts(singleLine);
		//printf("%d\n", i);
		fscanf(file, "%d", &i);
		if(count < (x*y)){
			mat1[k] = i;
			k++;
		}else{
			mat2[l] = i;
			l++;
		}
		count++;
		//printf("%d\n", i);
	}
	// printf("%d\n", mat1[0]);
	// printf("%d\n", mat1[1]);
	// printf("%d\n", mat1[2]);
	// printf("%d\n", mat1[3]);
	// printf("%d\n", mat1[4]);
	// printf("%d\n", mat1[5]);
	// printf("%d\n", mat1[6]);
	// printf("%d\n", mat1[7]);
	// printf("%d\n", mat1[8]);
	// printf("-----------\n");
	// printf("%d\n", mat2[0]);
	// printf("%d\n", mat2[1]);
	// printf("%d\n", mat2[2]);
	// printf("%d\n", mat2[3]);
	// printf("%d\n", mat2[4]);
	// printf("%d\n", mat2[5]);
	// printf("%d\n", mat2[6]);
	// printf("%d\n", mat2[7]);
	// printf("%d\n", mat2[8]);

	int g = 0;
	int h = 0;
	while(g < (x*y)){
		addition[g] = mat1[h] + mat2[h];
		g++;
		h++;
	}
	// printf("=============\n");
	// printf("%d\n", addition[0]);
	// printf("%d\n", addition[1]);
	// printf("%d\n", addition[2]);
	// printf("%d\n", addition[3]);
	// printf("%d\n", addition[4]);
	// printf("%d\n", addition[5]);
	// printf("%d\n", addition[6]);
	// printf("%d\n", addition[7]);
	// printf("%d\n", addition[8]);
	// fclose(file);

	int q = 0;
	int w = 0;
	int e = 0;
	for(q = 0; q < x; q++){
		for(w = 0; w < y; w++){
			matrix1[q][w] = addition[e];
			e++;
		}
		//e++;
	}
	// printf("=============\n");
	// printf("%d\n", matrix1[0][0]);
	// printf("%d\n", matrix1[0][1]);
	// printf("%d\n", matrix1[0][2]);
	// printf("%d\n", matrix1[1][0]);
	// printf("%d\n", matrix1[1][1]);
	// printf("%d\n", matrix1[1][2]);
	// printf("%d\n", matrix1[2][0]);
	// printf("%d\n", matrix1[2][1]);
	// printf("%d\n", matrix1[2][2]);
	
	for(q = 0; q < x; q++){
		for(w = 0; w < y; w++){
			printf("%d",matrix1[q][w]);
			if(w != w-1){
				if((q+1 == x) && (w+1 == y)){
					printf("\n");
				}else
					printf("\t");
			}
		}
		if(q != x-1)
			printf("\n");
	}
	fclose(file);
	return 0;
}