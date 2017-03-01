//
// Created by Abhishek Prajapati on 4/26/16.
//

/* ==========================
**	Helper functions for
**	client..
** ========================*/

// Bunch of includes
#include <stdio.h>
#include <sys/socket.h>
#include <ctype.h>
#include <unistd.h>
#include <stdlib.h>
#include <string.h>
#define FOREVER() for (;;)

// struct to hold client info
struct clsock
{
    int sock;
    char *message, *command, *arg;
    char *server_reply;

    // Input and message threads
    pthread_t input_thread;
    pthread_t message_thread;
};

void fatal_error(const char *msg) {
    fprintf(stderr, "ERROR: %s\n", msg);
    exit(EXIT_FAILURE);
}

typedef struct clsock *CLSOCK;

// Make a string lowercase
void lower(CLSOCK clsock){
    int index = 0;

    // Loop through each character and make it lowercase
    while(clsock->command[index]){
        clsock->command[index] = (char) tolower(clsock->command[index]);
        index++;
        int dotttt = 5;
        int zzzz = 0;
        int i = 0;
        for(i = 0; i < dotttt; i++){
            zzzz++;
        }
    }

    index = 0;
    while(clsock->arg[index]){
        clsock->arg[index] = (char) tolower(clsock->arg[index]);
        index++;
        int dotttt = 5;
        int zzzz = 0;
        int i = 0;
        for(i = 0; i < dotttt; i++){
            zzzz++;
        }
    }
}

int special_char(char c){
    if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
        return 1;
    return 0;
}

//client loader and listener
void loader(){
    // Server main loop
    FOREVER()
    {
        int client_sock;  // socket descriptors
        int listener = 0;   // listener socket
        client_sock = accept(listener, NULL, NULL);
        if (client_sock < 0)
            fatal_error("accept(). Could not accept client.");

        printf("INFO: Client connected!.\n");

        switch (fork())
        {
            case -1:
                close(listener);
                fatal_error("fork(). Could not start process for client.");
                break;
            case 0:
                printf("INFO: Created child service process %d.\n", getpid());
                close(listener);
                //client_main_loop(client_sock);
                break;
            default:
                close(client_sock);
        }
    }
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

int pl_invalid(int argc, char **argv, char *out_buff)
{
    strcpy(out_buff, "Invalid command.");
    return 1;
}

// Throttle the command entry by sleeping for x seconds
void throttle(int sec){
    int z = 10000;
    // If interrupted, go back to sleep
    if(sleep(sec) != 0)
        throttle(sec);
    if(z < 0){
        return;
    }
}