#include "data_structure.h"
#include <string.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

/*
 * Tokenizer type.  You need to fill in the type as part of your implementation.
 */

struct TokenizerT_ {
	char *original_string; //holds the original string
	char **token; //holds each token from the string
	char *temp; //used to itrate each token to make decision what kind of token it is
	int previous_position; //stores the previos position in token
	int line; //number of tokens in a string
};

typedef struct TokenizerT_ TokenizerT; //create a tokenizer object
int make_decision = 0; //used to make decision about the token, whether a token is aa word or a number or a float etc..
int comment = 0, string_quote = 0;

#define INITIAL_CAPACITY 10


/* comparator function for qsort*/
static int pairs_comparator(const void * a, const void * b){
	const index_pair_t * f = (index_pair_t *)a;
	const index_pair_t * s = (index_pair_t *)b;
	
	if (f->count < s->count)
		return 1;
	else if (f->count > s->count)
		return -1;

	return strcmp(f->file, s->file);
}

/* initialize mapping the list*/
void init_mapped_list(mapped_list_t * list){
	list->capacity = INITIAL_CAPACITY;
	list->records = malloc( sizeof(list_record_t) * list->capacity );
	list->size = 0;
}

/*
 * TKCreate creates a new TokenizerT object for a given token stream
 * (given as a string).
 * 
 * TKCreate should copy the arguments so that it is not dependent on
 * them staying immutable after returning.  (In the future, this may change
 * to increase efficiency.)
 *
 * If the function succeeds, it returns a non-NULL TokenizerT.
 * Else it returns NULL.
 *
 * You need to fill in this function as part of your implementation.
 */

TokenizerT *TKCreate( char * ts ) {
	TokenizerT *tk = (TokenizerT*)malloc(sizeof(TokenizerT)); //initilize memory for the string
	tk->original_string = malloc(strlen(ts)+2); //initilize memory to keep a copy of a string in original string
	strcat(tk->original_string,ts); //copy the string to original_string in tokenizer construction
	tk->token = malloc(sizeof(char*)*strlen(ts)+4); //initilize memory to stroe token
	tk->temp = malloc(strlen(ts)+4); //initilize memory to store temp
  return tk;  //return the tokenizer tk
}



/*create record of tokens*/
list_record_t create_record(const char * file, const char * word, int count){
	list_record_t record;
	record.word = malloc( sizeof(char) * (strlen(word)  +1) );
	strcpy(record.word, word);
	init_pairs_list(&record.list);
	add_pair(&record.list, file, count);
	return record;
}

/*
 * TKDestroy destroys a TokenizerT object.  It should free all dynamically
 * allocated memory that is part of the object being destroyed.
 *
 * You need to fill in this function as part of your implementation.
 */

void TKDestroy( TokenizerT * tk ) {
	free(tk->original_string);
	free(tk->temp);
	free(tk->token);
	free(tk);
}

/* add pair to the list*/
void add_pair(pairs_list_t * list, const char * file, int count){
	int i;
	for(i=0; i< list->size; ++i){
		if(strcmp(list->pairs[i].file, file) == 0){
			list->pairs[i].count += count;
			return;
		}
	}
	if(list->size + 1 > list->capacity){
		list->capacity *= 2;
		list->pairs = realloc(list->pairs, sizeof(index_pair_t) * list->capacity);
	}
	list->pairs[list->size] = create_pair(file, count);
	list->size += 1;
}

int special_char(char c){
	if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
		return 1;
	return 0;
}

/* comparator function for qsort*/
static int list_record_comparator(const void * a, const void * b){
	const list_record_t * f = (list_record_t *)a;
	const list_record_t * s = (list_record_t *)b;
	return strcmp(f->word, s->word);
}

/*
	This method will check if the given string is a C Keyword			
*/
int is_c_keyword(char *str){
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

/* sort the list*/
void sort_mapped_list(mapped_list_t * list){
	int i, j;
	qsort(list->records, list->size, sizeof(list_record_t), list_record_comparator);
	for (i = 0; i < list->size; ++i){
		for (j = 0; j < list->records[i].list.size; ++j)
			qsort(list->records[i].list.pairs, list->records[i].list.size, sizeof(index_pair_t), pairs_comparator);
	}
}

/*
	This method will pring the decision made in the above method
*/
void print_decision(int make_decision, char *str){
	switch(make_decision){
		case 1: printf("word\t\"%s\"\n", str); break;
		case 2: printf("integer\t\"%s\"\n", str); break;
		case 3:
			if(str[0] == '.') {printf("integer\t\"%s\"\n", str+1); break;}
			if(str[0] == '\\') {printf("integer\t\"%s\"\n", str+2); break;}
			printf("integer\t\"%s\"\n", str); break;
		case 8: printf("octal\t\"%s\"\n", str); break;
		case 16: printf("hex\t\"%s\"\n", str); break;
		case 10: printf("float\t\"%s\"\n", str); break;
		case 11: printf("float\t\"%s\"\n", str); break;	
		case 4: printf("\t\"%s\"\n", str); break;
		case 99 : printf("Not a valid hex value\t\"%s\"\n", str); break;
		case 100: printf("Not a valid float\t\"%s\"\n",str); break;
	}
}

/*
	This method will print c commands like ++, --, \t, etc.
*/	
void get_super_special_character (char c, char x, char y){
	if(c == '<' && x == '<' && y == '=') printf("Left shift and assignment\t\"%c%c%c\"\n",c,x,y);
	else if(c == '>' && x == '>' && y == '=') printf("right shift and assignment\t\"%c%c%c\"\n",c,x,y);
	else if(c == '<' && x == '=') printf("Less then or equal to\t\"%c%c\"\n",c,x);
	else if(c == '?' && x == ':') printf("Conditional Expression\t\"%c%c\"\n",c,x);
	else if(c == '&' && x == '=') printf("Bitwise And assignment\t\"%c%c\"\n",c,x);
	else if(c == '^' && x == '=') printf("Bitwise EOR assignment\t\"%c%c\"\n",c,x);
	else if(c == '|' && x == '=') printf("Bitwise IOR assignment\t\"%c%c\"\n",c,x);
	else if(c == '>' && x == '=') printf("Greater then or equal to\t\"%c%c\"\n",c,x);
	else if(c == '+' && x == '=') printf("Plus equals\t\"%c%c\"\n",c,x);
	else if(c == '-' && x == '=') printf("Minus equals\t\"%c%c\"\n",c,x);
	else if(c == '*' && x == '=') printf("Multiply equals\t\"%c%c\"\n",c,x);
	else if(c == '/' && x == '=') printf("Divide equals\t\"%c%c\"\n",c,x);
	else if(c == '+' && x == '+') printf("Increment\t\"%c%c\"\n",c,x);
	else if(c == '-' && x == '-') printf("Decrement\t\"%c%c\"\n",c,x);
	else if(c == '=' && x == '=') printf("Equals To\t\"%c%c\"\n",c,x);
	else if(c == '!' && x == '=') printf("Does not equals to\t\"%c%c\"\n",c,x);
	else if(c == '&' && x == '&') printf("Logic And\t\"%c%c\"\n",c,x);
	else if(c == '|' && x == '|') printf("Logic Or\t\"%c%c\"\n",c,x);
	else if(c == '<' && x == '<') printf("Left Shit\t\"%c%c\"\n",c,x);
	else if(c == '>' && x == '>') printf("Right Shift\t\"%c%c\"\n",c,x);
	else if(c == '%' && x == '=') printf("Modulo equals\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == 't') printf("ESCAPE - Tab\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == 'a') printf("ESCAPE - Alarm\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == 'b') printf("ESCAPE - Backspace\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == 'f') printf("ESCAPE - Formeteed\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == 'n') printf("ESCAPE - New Line\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == 'r') printf("ESCAPE - Carriage Return\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == 'v') printf("ESCAPE - Vertical Tab\t\"%c%c\"\n",c,x);
	else if(c == '\\' && x == '\\') printf("ESCAPE - Backslash\t\"%c%c\"\n",c,x);
	else if(c == '/' && x == '*') printf("Beginning of a block comment\t\"%c%c\"\n",c,x);
	else if(c == '*' && x == '/') printf("End of a block comment\t\"%c%c\"\n",c,x);
	else if(c == '`') printf("Acute\t\"%c\"\n",c);
	else if(c == '~') printf("Tilde\t\"%c\"\n",c);
	else if(c == '!') printf("Exclamation mark\t\"%c\"\n",c);
	else if(c == '@') printf("At\t\"%c\"\n",c);
	else if(c == '#') printf("Pound\t\"%c\"\n",c);
	else if(c == '$') printf("Dollar\t\"%c\"\n",c);
	else if(c == '%') printf("Modulo\t\"%c\"\n",c);
	else if(c == '^') printf("Caret\t\"%c\"\n",c);
	else if(c == '&') printf("Ampersand\t\"%c\"\n",c);
	else if(c == '*') printf("Asterisk\t\"%c\"\n",c);
	else if(c == '(') printf("Open Pren\t\"%c\"\n",c);
	else if(c == ')') printf("Close Pren\t\"%c\"\n",c);
	else if(c == '-') printf("Hyphen\t\"%c\"\n",c);
	else if(c == '_') printf("Underscore\t\"%c\"\n",c);
	else if(c == '=') printf("Equal\t\"%c\"\n",c);
	else if(c == '+') printf("Plus\t\"%c\"\n",c);
	else if(c == '[') printf("Open Bracket\t\"%c\"\n",c);
	else if(c == ']') printf("Close Bracket\t\"%c\"\n",c);
	else if(c == '{') printf("Open Brace\t\"%c\"\n",c);
	else if(c == '}') printf("Close Brace\t\"%c\"\n",c);
	else if(c == '|') printf("Pipe\t\"%c\"\n",c);
	else if(c == '\\') printf("Back slash\t\"%c\"\n",c);
	else if(c == '/') printf("Forward Slash\t\"%c\"\n",c);
	else if(c == ':') printf("Colon\t\"%c\"\n",c);
	else if(c == ';') printf("Semi colon\t\"%c\"\n",c);
	else if(c == '"') printf("Quote\t\"%c\"\n",c);
	else if(c == '\'') printf("Apostrophe\t\"%c\"\n",c);
	else if(c == '<') printf("Less Than\t\"%c\"\n",c);
	else if(c == '>') printf("Greater Than\t\"%c\"\n",c);
	else if(c == ',') printf("Comma\t\"%c\"\n",c);
	else if(c == '.') printf("Period\t\"%c\"\n",c);
	else if(c == '?') printf("Question Mark\t\"%c\"\n",c);
}






/*write the list to the json document format*/
void list2json(mapped_list_t * list, const char * file){
	FILE * fout = fopen(file, "w");
	int i,j;
	if (!fout){
		printf("Cannot write to %s \n", file);
		return;
	}

	fprintf(fout, "{\"list\" : [\n");
	for (i = 0; i< list->size; ++i)
	{
		fprintf(fout, "\t\t{\"%s\" : [\n", list->records[i].word);
		for (j = 0; j < list->records[i].list.size; ++j)
		{
			fprintf(fout, "\t\t\t{\"%s\" : %d}", list->records[i].list.pairs[j].file, list->records[i].list.pairs[j].count);
			if (j + 1 != list->records[i].list.size)
				fprintf(fout, ",");
			fprintf(fout, "\n");
		}
		fprintf(fout, "\t\t]}");
		if (i + 1 != list->size)
			fprintf(fout, ",");
		fprintf(fout, "\n");
	}

	fprintf(fout, "]}");
	printf("Parsed data was successfully saved to %s\n", file);
	fclose(fout);
}

/*
	This method will read in a charcter and make decisions wheter it is a word, number, float, etc.. (FSM)
	1->word, 2,3->integer , 8->octal, 16 ->hex, 10,11 ->float, 99 ->invalid hex, 100 ->invalid float
*/
void decisions_decisions(char c, char x, char y){
	switch(make_decision){
		case 0:
			//printf("entering case0 \n");
			if(isalpha(c)){ make_decision = 1; break; } //decision to make it a word
			else if(c == '0'){ make_decision = 2; break;}//decions to make if word strats with 0
			else if(isdigit(c)){ make_decision = 3; break;}//decisions to make if a number
			else if(special_char(c) == 1){ //its a special character
				if(string_quote > 0 || comment > 0) return;
				if(c == '/' && x == '/') goto SKIP;
				get_super_special_character(c,x,y);
				SKIP: ;
				make_decision = 0; break;
			}
		case 1:
			//printf("entering case1 \n");
			if(isalpha(c)|| isdigit(c)){ make_decision = 1; break;}  //still a word
			else{ make_decision = 1; break;} //word for sure
		case 2:
			//printf("entering case2 \n");
			if((c >= '0') && (c <= '7')){ make_decision = 8; break;}//might be octal
			else if( c == 'x' || c == 'X'){//might be hexa
				make_decision = 16;
				if(!isdigit(x) && !isalpha(x)) make_decision = 99; break;
			}else if(c == '.'){ make_decision = 10; break; }//might be float
			else{ make_decision = 3; break;}//integer for sure
		case 3:
			//printf("entering case3 \n");
			if(isdigit(c)){ make_decision = 3; }//still a number
			else if(c == '.'){ make_decision = 10; }//might be a float
			else{ make_decision = 3; }//integer for sure
			break;
		case 8: //checks for orctal
			if(c >= '0' && c <= '7') make_decision = 8;//if between 0-7 then octal
			else if(c == '.') make_decision = 10;
			else make_decision = 3; //else integer
			break;
		case 16:
			if((c >= '0' && c <= '9') || (c >= 'a' && c <= 'f') || (c >= 'A' && c <= 'F')) make_decision = 16; //checks for the correct hexa formet //still a hex
			 else make_decision = 99; //not a hex number anymore print error
			break;
		case 10: //check if still floting point
			if(isdigit(c)) make_decision = 10;//if number still good
			else if(c == 'E' || c == 'e') make_decision = 11;//if E or e then still good	
			else make_decision = 100; //else bad floating point number
			break;
		case 11: //checks for + - in floating
			if(isdigit(c)) make_decision = 11; //still a good floating point	
			if(c == '-' || c == '+') make_decision = 11;//still a good floating point
			break;
	}
	
}

/* destroys the list*/
void destroy_list(mapped_list_t * list){
	int i;
	int j;

	for (i = 0; i< list->size; ++i)
	{
		free(list->records[i].word);
		for (j = 0; j < list->records[i].list.size; ++j)
		{
			free(list->records[i].list.pairs[j].file);
		}
		list->records[i].list.size = 0;
		list->records[i].list.capacity = 0;
		free(list->records[i].list.pairs);
	}
	list->size = list->capacity = 0;
	free(list->records);
}

/* initialize the list to be paired*/
void init_pairs_list(pairs_list_t * list){
	list->capacity = INITIAL_CAPACITY;
	list->pairs = malloc( sizeof(index_pair_t) * list->capacity );
	list->size = 0;
}

/*
	This methos will call in the string from main and send in charaters to decisions_decisions method to make the decisions on what kind of token it is
*/
void decision(char *str){
	int i = 0;
	make_decision = 0;

	for(i = 0; i<strlen(str); i++){
		decisions_decisions(str[i], str[i+1], str[i+2]);

		if(string_quote > 0){ string_quote = 0; printf("Quoted String \t\"%s\"\n", str); return; }
		if(comment > 0){ comment = 0; printf("Comment\t\"%s\"\n", str); return; }
		if(special_char(str[i]) == 1 && special_char(str[i+1]) == 1 && special_char(str[i+2]) == 1) i+=3;
		else if(special_char(str[i])== 1 && special_char(str[i+1]) == 1) i+=2;
		else if((str[i] == '\\' && str[i+1] == 't') || (str[i] == '\\' && str[i+1] == 'a') || (str[i] == '\\' && str[i+1] == 'b') || (str[i] == '\\' && str[i+1] == 'f') || (str[i] == '\\' && str[i+1] == 'n') || (str[i] == '\\' && str[i+1] == 'r') || (str[i] == '\\' && str[i+1] == 'v'))i+=2;
	}
	
	if(is_c_keyword(str) == 1)
		return;
	print_decision(make_decision,str);
}

/*create pairs*/
index_pair_t create_pair(const char * word, int count){
	index_pair_t pair;
	pair.file = malloc( sizeof(char) * ( strlen(word) + 1) );
	strcpy(pair.file, word);
	pair.count = count;
	return pair;
}

/*add record to the list*/
void add_list_record(mapped_list_t * list, const char * file, const char * word, int count){
	int i;
	for(i=0; i< list->size; ++i){
		if(strcmp(list->records[i].word, word) == 0){
			add_pair(&list->records[i].list, file, count);
			return;
		}
	}
	if(list->size + 1 > list->capacity){
		list->capacity *= 2;
		list->records = realloc(list->records, sizeof(list_record_t) * list->capacity);
	}
	list->records[list->size] = create_record(file, word, count);
	list->size += 1;
}



