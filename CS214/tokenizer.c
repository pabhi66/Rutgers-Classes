/*
 * tokenizer.c
 	Abhishek Prajapati 
	Eric Cajuste	
 */
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>

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

int special_char(char c){
	if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
		return 1;
	return 0;
}

/*
 * TKGetNextToken returns the next token from the token stream as a
 * character string.  Space for the returned token should be dynamically
 * allocated.  The caller is responsible for freeing the space once it is
 * no longer needed.
 *
 * If the function succeeds, it returns a C string (delimited by '\0')
 * containing the token.  Else it returns 0.
 *
 * You need to fill in this function as part of your implementation.
 */

char **TKGetNextToken( TokenizerT * tk ) {
	int i = 0, j = 0, xyz = 0, abc =0, isfloat = 0, count = 0, iter = 0, trigger = 0; //local variables to keep track of the special cases
	int position = tk->previous_position; //assign position to previous position in original string i.e. where we stoped and made a token
	int line = tk->line; //assigne line
	tk->token[tk->line] = malloc(1000); //create space for token, will be cleared in the end

	

	while(isspace(tk->original_string[position])) position++; //skip through white spaces

	//This loop reads through the string and splits token according to its conditions
	while(tk->original_string[position] != ' '){
		tk->temp[j++] = tk->original_string[position]; //copy the current character into the temp string

		FIRST: ; //label to skip around 

		//This if statement checks for one special case like this one (123.dsf) and splits into different token
		if(tk->original_string[position+1] == '.' && isdigit(tk->original_string[position]) && isalpha(tk->original_string[position+2]))
			goto LABEL;

		//this if statement checks if its an escape charater then make it a one token
		else if((tk->original_string[position] == 't' && tk->original_string[position-1] == '\\') || (tk->original_string[position] == 'b' && tk->original_string[position-1] == '\\')
			||(tk->original_string[position] == 'n' && tk->original_string[position-1] == '\\') || (tk->original_string[position] == 'v' && tk->original_string[position-1] == '\\')
			|| (tk->original_string[position] == 'a' && tk->original_string[position-1] == '\\') || (tk->original_string[position] == 'f' && tk->original_string[position-1] == '\\')
			|| (tk->original_string[position] == 'r' && tk->original_string[position-1] == '\\')) goto LABEL;
		
		//This if statement saperates the special characters and splits sinto its own token with some rules given inside
		else if(!isalpha(tk->original_string[position]) && !isdigit(tk->original_string[position])) {

			
			//if statement to check if its a float or not
			 if(tk->original_string[position] == '.' && isdigit(tk->original_string[position-1])){
				 xyz++;
				 if(isalpha(tk->original_string[position+1]) && (tk->original_string[position+1] != 'e' || tk->original_string[position+1] != 'E')) goto LABEL;
				goto JMP;
			}

			//checks if proper float contains any of -, +, e, or E, if does then its a valid float
			else if((tk->original_string[position] == '-' || tk->original_string[position] == '+') && (tk->original_string[position-1] == 'e' || tk->original_string[position-1] == 'E') && isdigit(tk->original_string[position+1]))
				goto JMP;

			//this and next if statement checks if there is a comment on the line, if yes then it will copy the entire line into one token
			else if(tk->original_string[position] == '/' && tk->original_string[position+1] == '/'){
				tk->token[line][i++] = tk->original_string[position++];
				comment++;
				while(tk->original_string[position+1] != ' '){
					tk->temp[j++] = tk->original_string[position];
					tk->token[line][i++] = tk->original_string[position++];
				}
			}

			else if(tk->original_string[position] == '/' && tk->original_string[position+1] == '*'){
				int is_block_c = 0, index = position + 2;
				while(index <= strlen(tk->original_string)){
					if(tk->original_string[index] == '*' && tk->original_string[index+1] == '/'){is_block_c = 1; break;}
					index++;
				}
				if(is_block_c == 1){
					tk->token[line][i++] = tk->original_string[position++]; comment++;
					while(position != index+1){
						tk->temp[j++] = tk->original_string[position];
						tk->token[line][i++] = tk->original_string[position++];
					}
				}
			}

			//next two if statement  checks if its "String" or 'string'
			else if(tk->original_string[position] == '"'){
				int is_quote_c = 0, index = position + 1;
				while(index <= strlen(tk->original_string) - 1){
					if(tk->original_string[index] == '"'){is_quote_c++; break;}
					index++;
				}
				if(is_quote_c == 1){
					tk->token[line][i++] = tk->original_string[position++]; string_quote++;
					while(position != index){
						tk->temp[j++] = tk->original_string[position];
						tk->token[line][i++] = tk->original_string[position++];
					}
				}
			}

			else if(tk->original_string[position] == '\''){
				int is_quote_c = 0, index = position + 1;
				while(index <= strlen(tk->original_string) - 1){
					if(tk->original_string[index] == '\''){is_quote_c++; break;}
					index++;
				}
				if(is_quote_c == 1){
					tk->token[line][i++] = tk->original_string[position++]; string_quote++;
					while(position != index){
						tk->temp[j++] = tk->original_string[position];
						tk->token[line][i++] = tk->original_string[position++];
					}
				}
			}

			//this if statemnt checks for if there is a two special c keywords together like ++ -- \t \n  >>= <<= etc.... and keep them as one token insted of splitting
			else if( (tk->original_string[position] == '>' && tk->original_string[position+1] == '>' && tk->original_string[position+2] == '=') || (tk->original_string[position] == '<' && tk->original_string[position+1] == '<' && tk->original_string[position+2] == '=')
				||(tk->original_string[position] == '>' && tk->original_string[position+1] == '=')  || (tk->original_string[position] == '<' && tk->original_string[position+1] == '=') || (tk->original_string[position] == '+' && tk->original_string[position+1] == '=')
				||(tk->original_string[position] == '-' && tk->original_string[position+1] == '=')  || (tk->original_string[position] == '*' && tk->original_string[position+1] == '=')
				||(tk->original_string[position] == '/' && tk->original_string[position+1] == '=')  || (tk->original_string[position] == '+' && tk->original_string[position+1] == '+')
				||(tk->original_string[position] == '-' && tk->original_string[position+1] == '-')  || (tk->original_string[position] == '=' && tk->original_string[position+1] == '=')
				||(tk->original_string[position] == '!' && tk->original_string[position+1] == '=')  || (tk->original_string[position] == '&' && tk->original_string[position+1] == '&')
				||(tk->original_string[position] == '|' && tk->original_string[position+1] == '|')  || (tk->original_string[position] == '>' && tk->original_string[position+1] == '>')
				||(tk->original_string[position] == '<' && tk->original_string[position+1] == '<')  || (tk->original_string[position] == '%' && tk->original_string[position+1] == '=')
				||(tk->original_string[position] == '/' && tk->original_string[position+1] == '*')  || (tk->original_string[position] == '*' && tk->original_string[position+1] == '/')
				||(tk->original_string[position] == '\\' && tk->original_string[position+1] == 't') || (tk->original_string[position] == '\\' && tk->original_string[position+1] == 'a')
				||(tk->original_string[position] == '\\' && tk->original_string[position+1] == 'b') || (tk->original_string[position] == '\\' && tk->original_string[position+1] == 'f')
				||(tk->original_string[position] == '\\' && tk->original_string[position+1] == 'n') || (tk->original_string[position] == '\\' && tk->original_string[position+1] == 'r')
				||(tk->original_string[position] == '\\' && tk->original_string[position+1] == 'v') || (tk->original_string[position] == '\\' && tk->original_string[position+1] == '\\')
				|| (tk->original_string[position] == '?' && tk->original_string[position+1] == ':') || (tk->original_string[position] == '&' && tk->original_string[position+1] == '=')
				|| (tk->original_string[position] == '^' && tk->original_string[position+1] == '=') || (tk->original_string[position] == '|' && tk->original_string[position+1] == '=')) {
				goto JMP;//goto JMP; //if its super special characters then jump and read in the next charater
			}
			//if its just one character then make its own token
			LABEL: ;
			tk->token[line][i++] = tk->original_string[position++];
			tk->token[line][i] = '\0';
			tk->previous_position = position;
			memset(tk->temp, 0, sizeof(tk->temp));
  			return tk->token;
  		JMP: ;
  			tk->token[line][i++] = tk->original_string[position++]; //this is to keep the token going for special cases insted of saperating everytime it sees a special char
		}

		
		//this if statement checks if character is alphabet or a number and splits tokens accordingly 
		else if(isalpha(tk->original_string[position]) || isdigit(tk->original_string[position])){
			//checks if there is a float or not and splits accordingly
			if(isdigit(tk->original_string[position]) && tk->original_string[position+1] == '.'){ 
				isfloat++;
				 if(isfloat >= 2){ isfloat = 0; tk->token[line][i++] = tk->original_string[position++]; goto AHAH; }
				goto JUMP;
			}

			//checks if a proper float or not with E and =,- signes with the correct formet of prope float
			else if((isdigit(tk->original_string[position]) && (tk->original_string[position+1] == 'e' || tk->original_string[position+1] == 'E') /*&& (tk->original_string[position+2] == '-' || tk->original_string[position+2] == '+')*/)
					|| ((tk->original_string[position] == 'e' || tk->original_string[position] == 'E') && (tk->original_string[position+1] == '-' || tk->original_string[position+1] == '+')
						&& (isdigit(tk->original_string[position-1])) && isdigit(tk->original_string[position+2])) ) {
				if(xyz == 0){ tk->token[line][i++] = tk->original_string[position++]; goto AHAH; }
				goto JUMP;
			}

			//next two if statement checs if there is a hex decimal or not
			else if(tk->original_string[position] == '0' && (tk->original_string[position+1] == 'x' || tk->original_string[position+1] == 'X') && (tk->original_string[position-1] == 't' || tk->original_string[position-1] == 'a' || tk->original_string[position-1] == 'b' || tk->original_string[position-1] == 'f' || tk->original_string[position-1] == 'v' || tk->original_string[position-1] == 'n' || tk->original_string[position-1] == 'r') && (tk->original_string[position-2] == '\\')	){
				goto HEX;
			}
			else if(tk->original_string[position] == '0' && (tk->original_string[position+1] == 'x' || tk->original_string[position+1] == 'X') && !isdigit(tk->original_string[position-1]) && (!isalpha(tk->original_string[position-1]))) {
				HEX: ;
				tk->token[line][i++] = tk->original_string[position++];
				while(tk->original_string[position] != ' '){
					tk->temp[j++] = tk->original_string[position];
					if(isdigit(tk->original_string[position]) || isalpha(tk->original_string[position])){
						tk->token[line][i++] = tk->original_string[position++];
					 	if(!isdigit(tk->original_string[position+1]) && !isalpha(tk->original_string[position+1])) goto FIRST;
					}
				}
			}

			//saperates the word followed by number like this (123abc -> 123 abc) two different tokens
			else if(isdigit(tk->original_string[position]) && isalpha(tk->original_string[position+1])){
				tk->token[line][i++] = tk->original_string[position++];
				goto AHAH;
			}

			//saperates any special characters from between words and numbers
			else if(!isalpha(tk->original_string[position+1]) && !isdigit(tk->original_string[position+1])) {
				tk->token[line][i++] = tk->original_string[position++];
				goto AHAH;
			}

			//if its a letter copy everything until hit a special charater or space
			else if(isalpha(tk->original_string[position])){
				tk->token[line][i++] = tk->original_string[position++];

				while(tk->original_string[position] != ' '){
					tk->temp[j++] = tk->original_string[position];
					if(isdigit(tk->original_string[position]) || isalpha(tk->original_string[position])){
						tk->token[line][i++] = tk->original_string[position++];
						if(!isdigit(tk->original_string[position]) && !isalpha(tk->original_string[position])){
							tk->token[line][i] = '\0';
							tk->previous_position = position;
							memset(tk->temp, 0, sizeof(tk->temp));
  							return tk->token;
							goto FIRST;
						}
					}
				}
			}
		JUMP: ;
			tk->token[line][i++] = tk->original_string[position++];
		}
		else break;
	}
	//saperates string(word) into its own saperate token
	AHAH: ;
	tk->token[line][i] = '\0';
	tk->previous_position = position;
	memset(tk->temp, 0, sizeof(tk->temp));

  return tk->token;
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

/*
 * main will have a string argument (in argv[1]).
 * The string argument contains the tokens.
 * Print out the tokens in the second string in left-to-right order.
 * Each token should be printed on a separate line.
 */

int main(int argc, char **argv) {

	//if input string is null or more then one argument then print error
	if(argc != 2){ printf("Please enter a valid string.\n" ); return 0;}

	TokenizerT *tk = TKCreate(argv[1]); //create a tokenizer object
	char **str; //array to sore each tokens from the string

	//while the string does not end keep getting the token and stroe it in str array
	while(tk->original_string[tk->previous_position] != '\0'){
		str = TKGetNextToken(tk);
		decision(str[tk->line]);
		tk->line++;
	}
	TKDestroy(tk);
  return 0;
}
