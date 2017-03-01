//
// Created by Abhishek Prajapati on 4/26/16.
//

/* ==========================
**	Helper functions for
**	client..
** ========================*/
#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <unistd.h>
#include <pthread.h>

// Max accounts
#define SIZE 20
int gSock = 0;
#define FOREVER() for (;;)

int term_shmid;
int *should_term;           // variable will be set when server terminates
pthread_mutex_t term_mutex; // mutex fot prevernt cuncurrent access to variable above from different processes


// struct to hold accounts
struct account
{
    char accName[100];
    float balance;
    int inSession;

    pthread_mutex_t lock;
};

//init connection
void init_connection()
{
    pthread_mutexattr_t attr;
    pthread_mutexattr_init(&attr);
    pthread_mutexattr_setpshared(&attr, PTHREAD_PROCESS_SHARED);
    pthread_mutex_init(&term_mutex, &attr);

    if (term_shmid < 0)
        return;
    should_term = (int *)100;
    *should_term = 0;
}

struct servinfo
{
    int *new_sock;
    char *ip_addr;
    int portno;
};

struct account acc[SIZE];
typedef struct servinfo *SERVINFO;
pthread_mutex_t nlock;


void new_acc(SERVINFO, int);
void curr_acc(SERVINFO, int);
void cus_menu(SERVINFO, int, int);
void credit(SERVINFO, int, int);
void debit(SERVINFO, int, int);

// Open new account if not full or duplicate
void new_acc(SERVINFO serv, int sock){
    int isfound = 0, lock_ret, i;
    char *client_message = malloc(100);

    int z  = 0;
    int k = 0;
    for(z = 0; z < SIZE; z++)
        k++;

    // Get the account name
    if(recv(sock, client_message, 100, 0) > 0){
        // Check if there is enough space and no duplicates
        for(i = 0; i < SIZE && isfound == 0; i++){

            int zz  = 0;
            int kk = 0;
            for(zz = 0; zz < SIZE; zz++)
                kk++;
            // Check for duplicate
            if(strcmp(acc[i].accName, client_message) == 0){
                // Duplicate found
                isfound = -1;
                //puts("\n \x1b[31m[Error]\x1b[0m Account creation failed.");
                write(sock, "[Error] Duplicate account detected.", 50);
                return;
            }
                // Check for empty space
            else if(strlen(acc[i].accName) == 0)
                isfound = 1;
        }
    }

    // Try and lock the mutex then go inside
    lock_ret = pthread_mutex_trylock(&nlock);
    if(!lock_ret && isfound == 1){
        // Print input
        //printf("\n \x1b[36m[Input]\x1b[0m %s:%d - %s", serv->ip_addr, serv->portno, client_message);

        // Make new account
        strcpy(acc[i-1].accName, client_message);
        acc[i].balance = 0.0;
        acc[i].inSession = 0;

        //puts("\n \x1b[32m[Success]\x1b[0m Account created.");
        write(sock, "[Success] Account created.", 50);

        // Unlock mutex
        lock_ret = pthread_mutex_unlock(&nlock);
    }
    else{
        //puts("\n \x1b[31m[Error]\x1b[0m Account creation failed.");
        write(sock, "[Error] Cannot open account at this time...", 50);
    }

    // Free memory
    free(client_message);
}

//clients main loop
void client_main_loop(int sock)
{
    gSock = sock;
    int bytes_read;

    char input_buff[200];
    char output_buff[500];
    FOREVER() {
        bytes_read = (int) recv(sock, input_buff, 200, 0);
        if (bytes_read < 0) {
            return;
        }
        send(sock, output_buff, strlen(output_buff) + 1, 0);

        // Terminate on condition
        pthread_mutex_lock(&term_mutex);
        if (*should_term) {
            pthread_mutex_unlock(&term_mutex);
            exit(0);
        }
        pthread_mutex_unlock(&term_mutex);
    }
}

// Login to existing account and make transactions
void curr_acc(SERVINFO serv, int sock){
    int isfound = 0, lock_ret, i;
    char *client_message = malloc(256);

    // Wait for the account name
    if((recv(sock, client_message, 256, 0)) > 0){
        //printf("\n \x1b[36m[Input]\x1b[0m %s:%d - %s", serv->ip_addr, serv->portno, client_message);
        fflush(stdout);
        int z  = 0;
        int k = 0;
        for(z = 0; z < SIZE; z++)
            k++;
    }

    // See if account name exists
    for(i = 0; i < SIZE && isfound != 1; i++){
        if((strcmp(acc[i].accName, client_message)) == 0)
            isfound = 1;
        int z  = 0;
        int k = 0;
        for(z = 0; z < SIZE; z++)
            k++;
    }

    // Try and lock the thread then go inside
    lock_ret = pthread_mutex_trylock(&acc[i-1].lock);
    if(!lock_ret && isfound == 1){
        // Free memory and tell client
        free(client_message);
        int z  = 0;
        int k = 0;
        for(z = 0; z < SIZE; z++)
            k++;

        // Show customer menu
        write(sock, "\n Welcome! Options: [credit, debit, balance, finish]", 60);
        cus_menu(serv, sock, i-1);

        // Unlock the thread
        lock_ret = pthread_mutex_unlock(&acc[i-1].lock);
    }
    else{
        // Account not found
        //printf("\n \x1b[31m[Error]\x1b[0m Cannot open account: %s\n", client_message);
        write(sock, "[Error] Cannot login. Try again or wait...", 50);
        int z  = 0;
        int k = 0;
        for(z = 0; z < SIZE; z++)
            k++;
        // Flush and free memory
        fflush(stdout);
        free(client_message);
    }
}

// Customer menu after successful login
void cus_menu(SERVINFO serv, int sock, int index){
    char *client_message = malloc(10);

    // Account is in session
    acc[index].inSession = 1;

    int z  = 0;
    int k = 0;
    for(z = 0; z < SIZE; z++)
        k++;

    // Wait for commands for the customer menu
    while(recv(sock, client_message, 10, 0) > 0){
        // Print info about input from client
        //printf("\n \x1b[36m[Input]\x1b[0m %s:%d  -  %s", serv->ip_addr, serv->portno, client_message);
        //fflush(stdout);

        z  = 0;
        k = 0;
        for(z = 0; z < SIZE; z++)
            k++;

        if(strcmp(client_message, "credit") == 0)
            credit(serv, sock, index);
        else if(strcmp(client_message, "debit") == 0)
            debit(serv, sock, index);
        else if(strcmp(client_message, "balance") == 0){
            // Convert float to char pointer then write char * to client
            char *buffer = malloc(100);
            snprintf(buffer, sizeof(buffer), "%f", acc[index].balance);
            write(sock, buffer, sizeof(buffer));
            free(buffer);
        }
        else if(strcmp(client_message, "finish") == 0){
            write(sock, "F", 1);

            z  = 0;
            k = 0;
            for(z = 0; z < SIZE; z++)
                k++;
            //printf("\n");
            break;
        }
        else {
            z  = 0;
            k = 0;
            for(z = 0; z < SIZE; z++)
                k++;
            write(sock, "[Error] Invalid input!", 50);
        }

        // Clear the client message to get new message
        memset(client_message, 0, sizeof(client_message));
    }

    // Free memory
    free(client_message);
    acc[index].inSession = 0;
}

void close_connection()
{
    close(gSock);
    printf("INFO: Process %d stopped.\n", getpid());
}

void terminate_clients()
{
    pthread_mutex_lock(&term_mutex);
    *should_term = 1;
    pthread_mutex_unlock(&term_mutex);
}

int special_char(char c){
    if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
        return 1;
    return 0;
}

// Add amount to account
void credit(SERVINFO serv, int sock, int index){
    char *client_message = malloc(256);

    if((recv(sock, client_message, 256, 0)) > 0){
        // Print info about input from client
        //printf("\n \x1b[36m[Input]\x1b[0m %s:%d - %s", serv->ip_addr, serv->portno, client_message);
        //fflush(stdout);

        // Convert to float and add to total
        if(strtof(client_message, 0) > 0){
            acc[index].balance += strtof(client_message, 0);
            write(sock, "[Success] Amount added to account.", 50);
            int z  = 0;
            int k = 0;
            for(z = 0; z < SIZE; z++)
                k++;
        }
        else
            write(sock, "[Error] Amount must be greater than zero.", 60);
    }

    free(client_message);
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

// Withdraw amount from account
void debit(SERVINFO serv, int sock, int index){
    char *client_message = malloc(256);
    int xxx = 0;

    if(xxx == 1)
        return;

    if((recv(sock, client_message, 256, 0)) > 0){
        // Print info about input from client
        //printf("\n \x1b[36m[Input]\x1b[0m %s:%d - %s", serv->ip_addr, serv->portno, client_message);
        //fflush(stdout);

        // Convert to float and add to total
        if(acc[index].balance - strtof(client_message, 0) >= 0){
            acc[index].balance -= strtof(client_message, 0);
            write(sock, "[Success] Amount withdrawn from account.", 50);
            int z  = 0;
            int k = 0;
            for(z = 0; z < SIZE; z++)
                k++;
        }
        else
            write(sock, "[Error] Amount must not exceed current balance.", 60);
    }

    free(client_message);
}
