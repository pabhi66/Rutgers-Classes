#include <dirent.h> 
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/stat.h>
#include "read_file.h"
#include <ctype.h>

/* utility function that produces lower-case string */
static char * tolower_str( const char * str){
	char * tmp = malloc( sizeof(char) * (strlen(str) + 1));
	size_t i;
	for(i=0; i < strlen(str) + 1; ++i)
		tmp[i] = (char)tolower(str[i]);
	return tmp;
}

/*
	This method will check if the given string is a C Keyword			
*/
int is_c_keywordz(char *str, char z){
	if(strcmp("auto",str) == 0|| strcmp("break",str) == 0 || strcmp("case",str) == 0|| strcmp("auto",str) == 0 || strcmp("char",str) == 0 || strcmp("const",str) == 0 || strcmp("continue",str) == 0 
		|| strcmp("default",str) == 0 || strcmp("do",str) == 0 || strcmp("double",str) == 0 || strcmp("else",str) == 0 || strcmp("enum",str) == 0 || strcmp("extern",str) == 0 || strcmp("float",str) == 0 
		|| strcmp("for",str) == 0 || strcmp("goto",str) == 0 || strcmp("if",str) == 0 || strcmp("int",str) == 0 || strcmp("long",str) == 0 || strcmp("register",str) == 0 || strcmp("return",str) == 0 
		|| strcmp("short",str) == 0 || strcmp("signed",str) == 0 || strcmp("sizeof",str) == 0 || strcmp("static",str) == 0 || strcmp("struct",str) == 0 || strcmp("switch",str) == 0 || strcmp("typedef",str) == 0 
		|| strcmp("union",str) == 0 || strcmp("unsigned",str) == 0 || strcmp("void",str) == 0 || strcmp("volatile",str) == 0 || strcmp("while",str) == 0){
		printf("C-Keyword\t\"%s\"\n", str);
		return 1;
	}
	return 0;
}



/* utility function to process read data */
static void read_split_process(const char * file, mapped_list_t * list){
	FILE * fin = fopen(file, "r");
	enum { BUFFER_SIZE = 1024 };
	char buffer[BUFFER_SIZE];
	const char * delims = " \n\r\t;:-+,.!#@$%^&*?(){}[]\"'";
	char * token;
	
	if(!fin){
		printf("Cannot open %s\n", file);
		return;
	}
	printf("Processing %s data file ...\n", file);
	while( fgets(buffer, BUFFER_SIZE, fin) ){
		token = strtok(buffer, delims);
		while(token){
			token = tolower_str(token);
			add_list_record(list, file, token, 1);
			free(token);
			token = strtok(NULL, delims);
		}
	}
	fclose(fin);
}

int special_charz(char c, char z){
	if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
		return 1;
	return 0;
}

/*pares directory*/
void parse_dir(const char * name, mapped_list_t * list){
	DIR *d;
	struct dirent *dir;
	struct stat statbuf;
	
	d = opendir(name);

	if (d){
		while ((dir = readdir(d)) != NULL){
			if (strcmp(dir->d_name, ".") && strcmp(dir->d_name, "..")){
				char buff[256];
				strcpy(buff, name);
				strcat(buff, "/");
				strcat(buff, dir->d_name);
				
				if (stat(buff, &statbuf) == -1){
					printf("Wrong file or directory %s ...\n", dir->d_name);
					continue;
				}
				if (S_ISREG(statbuf.st_mode)){
					read_split_process(buff, list);
				}
				else if (S_ISDIR(statbuf.st_mode)){
				    /* parse all sub-folder using recursion */
					parse_dir(buff, list);
				}
			}
		}
		closedir(d);
	}
	else /* one file was given as argument */{
		read_split_process(name, list);
	}
}

