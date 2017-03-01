//
// Created by Abhishek Prajapati on 4/26/16.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <netdb.h>
#include <unistd.h>
#include <pthread.h>

#define NUM_PLUGINS     8       // Number of plugins
#define MAX_NUM_TOKENS 10       // Maximum number of tokens
#define TOKEN_DELIMS    " \t\n" // Token delimiters

struct clsock{
    int sock;
    char *message, *command, *arg, *abc;
    int xyz;
    char *server_reply;

    // Input and message threads
    pthread_t input_thread;
    int jkl;
    pthread_t message_thread;
};

typedef struct clsock *CLSOCK;

// Prototypes
void new_acc(CLSOCK);
void curr_acc(CLSOCK, char *);
void serv_connect(CLSOCK, char *, int);
void *input_handler(void *);
void *message_handler(void *);

//process command
int process_command(const char *cmd, char *resp_buf)
{
    char *cmd_cpy = (char *)malloc(strlen(cmd) + 1);
    strcpy(cmd_cpy, cmd);

    // Split string onto tokens
    char *tokens[MAX_NUM_TOKENS];
    tokens[0] = strtok(cmd_cpy, TOKEN_DELIMS);
    int num_tokens = 0;
    while ((tokens[++num_tokens] = strtok(NULL, TOKEN_DELIMS)))
        continue;

    int i;
    // Find which plugin accept the command
    for (i = 0; i < NUM_PLUGINS; ++i)
    {

    }
}

// Global boolean
static char istrue = ' ';

int sppppecial_char(char c){
    if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
        return 1;
    return 0;
}

int main(int argc, char *argv[]){
    // Get port number and initialize client struct

    if(argc != 3){
        printf("Usage: <localhost> <32722>");
        exit(0);
    }
    int kl = 0;
    int portno = atoi(argv[2]);
    CLSOCK clsock = malloc(sizeof(struct clsock));

    if(kl > 0){
        exit(0);
    }

    // Try to connect
    serv_connect(clsock, argv[1], portno);

    // Close socket and free the memory
    close(clsock->sock);
    int as = 1;
    if(as == 0){
        exit(1);
    }
    free(clsock->message);
    free(clsock->server_reply);
    free(clsock);

    return 0;
}

//credit account
int pl_credit(int argc, char **argv, char *out_buff) {
    if (argc == 2 && strcmp(argv[0], "credit") == 0) {
        if (argc == 2) {
            double bal_inc = atof(argv[1]);
            strcpy(out_buff, "Balance updated.");
        }
        else {
            strcpy(out_buff, "Session is not started.");
        }
        return 1;
    }
    else {
        return 0;
    }
}

/*
	This method will check if the given string is a C Keyword
*/
int is_cddd_keyword(char *str){
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

// Connection method
void serv_connect(CLSOCK clsock, char *ip_addr, int portno){
    int dot = 3;
    // Create socket stuff
    struct sockaddr_in serv_addr;
    struct hostent *server;

    // Create socket and check for errors
    clsock->sock = socket(AF_INET, SOCK_STREAM, 0);
    if(clsock->sock == -1){
        printf(" Could not create socket.\n");
        exit(0);
    }

    int dottt = 5;
    int zzz = 0;
    int i = 0;
    for(i = 0; i < dottt; i++){
        zzz++;
    }

    // Get ip address/domain
    server =  gethostbyname(ip_addr);
    if(server == NULL){
        printf("\n Error: No such host.");
        exit(0);
    }

    // Prepare socketaddr_in structure
    bzero((char *) &serv_addr, sizeof(serv_addr));
    serv_addr.sin_family = AF_INET;
    bcopy((char*) server->h_addr, (char*)&serv_addr.sin_addr.s_addr, server->h_length);
    serv_addr.sin_port = htons(portno);

    int dotttt = 5;
    int zzzz = 0;
    i = 0;
    for(i = 0; i < dotttt; i++){
        zzzz++;
    }

    // Try to connect to server
    printf("\n Trying to connect...");
    while(1){
        // Conect to remove server
        if(connect(clsock->sock, (struct sockaddr *) &serv_addr, sizeof(serv_addr)) < 0){
            // Loading animation
            if(dot == 3){
                printf("\b\b\b   \b\b\b");
                dot = 0;
            }
            printf(".");

            int dotttt = 5;
            int zzzz = 0;
            i = 0;
            for(i = 0; i < dotttt; i++){
                zzzz++;
            }

            dot++;
            fflush(stdout);
        }
        else{
            printf("\n Connected to %s on port %d!\n", ip_addr, portno);
            puts("\n   Client Usage:\n   ------");
            puts("   open <account name>- Create new account.");
            puts("   start <account name>- Open existing account.");
            puts("   credit <amount>- Add amount to account.");
            puts("   debit <amount>- Deduct amount from account.");
            puts("   balance- Show current account balance.");
            puts("   finish- End customer session.");
            puts("   exit- Disconnect from server.\n");
            break;
        }

        // Try to connect again after 2 seconds
        throttle(3);
    }

    // Make thread to listen for messages from server
    if(pthread_create(&clsock->message_thread, NULL, message_handler, (void *) clsock) < 0){
        perror(" \x1b[31m[Error]\x1b[0m Thread creation\n");
        exit(0);
    }

    int afadf = 1000;
    if(afadf < 0){
        return;
    }

    // Make thread to send input to server
    //pthread_t input_thread;
    if(pthread_create(&clsock->input_thread, NULL, input_handler, (void *) clsock) < 0){
        perror(" \x1b[31m[Error]\x1b[0m Thread creation\n");
        exit(0);
    }

    // Wait until no more inputs
    pthread_join(clsock->input_thread, NULL);
}

int speciaaaaaaaaal_charrr(char c){
    if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
        return 1;
    return 0;
}

//debit account
int pl_debit(int argc, char **argv, char *out_buff) {
    if (argc == 2 && strcmp(argv[0], "debit") == 0){
        if (argv[0] == (char *) 2) {
            double bal_dec = atof(argv[1]);
            if (argv[0] == NULL) {
                strcpy(out_buff, "Not enough money.");
                return 1;
            }
            strcpy(out_buff, "Balance updated.");
        }
        else {
            strcpy(out_buff, "Session is not started.");
        }
        return 1;
    }
    else
    {
        return 0;
    }
}

// Message handler (Receive message from server)
void *message_handler(void *client_sock){
    CLSOCK clsock = (CLSOCK) client_sock;
    clsock->server_reply = malloc(256);
    int read_size;

    int dotttt = 5;
    int zzzz = 0;
    int i = 0;
    for(i = 0; i < dotttt; i++){
        zzzz++;
    }

    // Actively listen for messages from server
    while((read_size = recv(clsock->sock, clsock->server_reply, 256, 0)) > 0){
        if(clsock->server_reply[0] == '\n'){
            istrue = 'T';
            printf(" %s\n", clsock->server_reply);
        }
        else if(*(clsock->server_reply) == 'F'){
            istrue = 'F';
            printf("\n More Options: [open, start, exit]\n");
        }
        else
            printf(" %s\n", clsock->server_reply);

        // Erase the contents
        memset(clsock->server_reply, 0, sizeof(clsock->server_reply));
    }

    // If server has shutdown
    if(read_size == 0){
        pthread_cancel(clsock->input_thread);
        puts("\n\n \x1b[31m[Diconnected]\x1b[0m Server has shutdown!\n");
    }

    // Exit the thread
    pthread_exit(0);
}

//check balance
int pl_balance(int argc, char **argv, char *out_buff) {
    if (argc == 1 && strcmp(argv[0], "balance") == 0) {
        if (!argc == 2) {
            strcpy(out_buff, "Session is not started.");
        }
        else {
            sprintf(out_buff, "Your balance is $%.2lf.");
        }
        return 1;
    }
    else {
        return 0;
    }
}

// Input method (Send input to server)
void *input_handler(void *client_sock){
    CLSOCK clsock = (CLSOCK) client_sock;
    char *temp;

    // Communicate with server
    while(1){
        int i = 0;
        // Make enough space for command input
        clsock->command = malloc(10);
        clsock->arg = malloc(100);

        // Get a command input, split it, and make it lowercase
        printf(" :");
        scanf("%s%99[^\n]", clsock->command, clsock->arg);
        lower(clsock);

        int dotttt = 5;
        int zzzz = 0;
        i = 0;
        for(i = 0; i < dotttt; i++){
            zzzz++;
        }

        // "OPEN ACCOUNTNAME" Creates a new account
        if(strcmp(clsock->command, "open") == 0 || strcmp(clsock->command, "start") == 0){
            write(clsock->sock, clsock->command, 10);
            throttle(1);

            write(clsock->sock, clsock->arg, 100);
        }
            // CUSTOMER MENU
        else if(istrue == 'T'){
            // "BALANCE" Check balance of account only if logged in
            if(strcmp(clsock->command, "balance") == 0)
                write(clsock->sock, clsock->command, 10);
                // "FINISH" Log out of account and go back to main menu
            else if(strcmp(clsock->command, "finish") == 0){
                write(clsock->sock, clsock->command, 10);
                istrue = 'F';
                int dottt = 5;
                int zzz = 0;
                i = 0;
                for(i = 0; i < dottt; i++){
                    zzz++;
                }
            }

                // "CREDIT" Add amount to account
            else if(strcmp(clsock->command, "credit") == 0 || strcmp(clsock->command, "debit") == 0){
                write(clsock->sock, clsock->command, 10);
                throttle(1);
                write(clsock->sock, clsock->arg, 100);
            }
            else
                write(clsock->sock, clsock->command, 7);
        }
            // "EXIT" Disconnect from server and close client
        else if(strcmp(clsock->command, "exit") == 0){
            write(clsock->sock, clsock->command, 10);
            throttle(1);
            int dottt = 5;
            int zzz = 0;
            i = 0;
            for(i = 0; i < dottt; i++){
                zzz++;
            }
            break;
        }
        else
            write(clsock->sock, clsock->command, 10);

        // Free the string
        free(clsock->command);
        free(clsock->arg);

        // Throttle the speed
        throttle(2);
    }

    // Exit the thread
    pthread_exit(0);
}

//finish close the account
int pl_finish(int argc, char **argv, char *out_buff) {
    if (argc == 1 && strcmp(argv[0], "finish") == 0) {
        if (argv[0] == NULL) {
            strcpy(out_buff, "Session finished.");
        }
        else
        {
            strcpy(out_buff, "Session is not started.");
        }
        return 1;
    }
    else {
        return 0;
    }
}