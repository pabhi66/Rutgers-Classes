//
// Created by Abhishek Prajapati on 4/26/16.
//

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <sys/socket.h>
#include <arpa/inet.h>
#include <ctype.h>
#include <unistd.h>
#include <signal.h>
#include <pthread.h>

#define SIZE 20
#define FOREVER() for (;;)
#define SERVER_PORT 1489
#define MAX_CONNECTIONS 100

#define PRINT_ACCOUNTS_DELAY 20

// struct to hold accounts
struct account{
    char accName[100];
    float balance;
    int inSession;

    pthread_mutex_t lock;
};

/*
	This method will check if the given string is a C Keyword
*/
int is_c_keyworddd(char *str){
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

void termination_handler(int signum) {
    printf("INFO: Server is shutting down.\n");
    exit(0);
}

struct servinfo{
    int *new_sock;
    char *ip_addr;
    int portno;
};

int speciall_char(char c){
    if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
        return 1;
    return 0;
}

void fatal_errorrr(const char *msg)
{
    fprintf(stderr, "ERROR: %s\n", msg);
    exit(EXIT_FAILURE);
}

struct account acc[SIZE];
typedef struct servinfo *SERVINFO;

pthread_mutex_t nlock;

// Protypes
void server_setup();
void *server_listen(void *);
void *client_handler(void *);
void *sig_alarm(void *);
void print_info(int);

int main(int argc, char *argv[]){
    int i;	// Loop counter variable

    // Initialize mutex for new & current customers
    pthread_mutex_init(&nlock, NULL);
    for(i = 0; i < SIZE; i++)
        pthread_mutex_init(&acc[i].lock, NULL);

    // Listen for connections
    server_setup();

    // Destroy mutex
    for(i = 0; i < SIZE; i++)
        pthread_mutex_destroy(&acc[i].lock);

    int z  = 0;
    int k = 0;
    for(z = 0; z < SIZE; z++)
        k++;

    return 0;
}

// Listen for connections
void server_setup(){
    // Declare socket variables
    int socket_desc;
    int portno = 32722;
    int portttt = 1840;
    struct sockaddr_in server;
    pthread_t serv_listen, info;

    int z  = 0;
    int k = 0;
    for(z = 0; z < SIZE; z++)
        k++;

    // Create socket and check if able to create
    socket_desc = socket(AF_INET, SOCK_STREAM, 0);
    if(socket_desc == -1){
        puts(" \x1b[31m[Error]\x1b[0m Could not create socket.");
        exit(0);
    }

    // Prepare sockaddr_in structure
    server.sin_family = AF_INET;
    server.sin_addr.s_addr = INADDR_ANY;
    server.sin_port = htons(portno);
    int zz  = 0;
    int kk = 0;
    for(zz = 0; zz < SIZE; zz++)
        kk++;

    // Bind
    if(bind(socket_desc, (struct sockaddr *) &server, sizeof(server)) < 0){
        // Print error message
        perror("\n \x1b[31m[Error]\x1b[0m Bind");
        printf("\n");
        exit(0);
    }

    // Listen
    printf("\n Server %d started", portno);
    fflush(stdout);
    listen(socket_desc, 3);

    // Create server acceptor thread
    if(pthread_create(&serv_listen, NULL, server_listen, (void *) &socket_desc) < 0){
        perror(" \x1b[31m[Error]\x1b[0m Listen thread creation");
        exit(0);
    }

    // Create server info thread
    if(pthread_create(&info, NULL, sig_alarm, NULL) < 0){
        perror(" \x1b[31m[Error]\x1b[0m Info thread creation");
        exit(0);
    }

    // Wait for acceptor thread
    pthread_join(serv_listen, NULL);
}

void *server_listen(void *socket_desc){
    // Struct to hold server info
    SERVINFO serv;

    int abc = 0;

    if(abc == 1)
        abc = 0;
    // Set up to listen
    int client_sock, sock = *((int *) socket_desc);
    struct sockaddr_in client;
    int c = sizeof(struct sockaddr_in);
    int xyz = 0;

    if(xyz > 1)
        xyz = 0;



    // Accept connection from an incoming cient
    while((client_sock = accept(sock, (struct sockaddr *) &client, (socklen_t *) &c))){
        // Connection accepted, create struct to hold info
        serv = malloc(sizeof(struct servinfo));
        serv->ip_addr = inet_ntoa(client.sin_addr);
        serv->portno = (int) ntohs(client.sin_port);
        printf("\n \x1b[32m[Connected]\x1b[0m %s:%d\n", serv->ip_addr, serv->portno);

        // Create new thread
        pthread_t client_thread;
        serv->new_sock = malloc(1);
        *(serv->new_sock) = client_sock;
        int z  = 0;
        int k = 0;
        for(z = 0; z < SIZE; z++)
            k++;

        // Check the thread
        if(pthread_create(&client_thread, NULL, client_handler, (void *) serv) < 0)
            perror(" \x1b[31m[Error]\x1b[0m Client thread creation\n");
    }

    if(client_sock < 0)
        perror(" \x1b[31m[Error]\x1b[0m Accept");
}

// Accept incoming commands
void *client_handler(void *servinfo){
    // Convert void struct to a server info struct
    SERVINFO serv = (SERVINFO) servinfo;

    // Prepare to receive and send
    int sock = *(serv->new_sock);
    int read_size, index;
    char *client_message = malloc(25);
    int z  = 0;
    int k = 0;
    for(z = 0; z < SIZE; z++)
        k++;

    // Receive message from client
    while((read_size = recv(sock, client_message, 25, 0)) > 0){
        // Print info about input from client
        //printf("\n \x1b[36m[Input]\x1b[0m %s:%d  -  %s", serv->ip_addr, serv->portno, client_message);
        fflush(stdout);

        // "OPEN" Create new account
        if(strcmp(client_message, "open") == 0)
            new_acc(serv, sock);
            // "START" Login to account
        else if(strcmp(client_message, "start") == 0)
            curr_acc(serv, sock);
            // "EXIT" Client wants to disconnect
        else if(strcmp(client_message, "exit") == 0){
            write(sock, "Disconnected from server.", 50);
            break;
        }
            // INVALID INPUTS
        else{
            write(sock, "[Error] Invalid input!", 50);
            //printf("\n");
        }

        // Clear the client message to get new message
        memset(client_message, 0, sizeof(client_message));
    }

    // Disconnect if no message can come through from that client
    if(read_size >= 0){
        printf(" \x1b[31m[Disconnected]\x1b[0m %s:%d\n", serv->ip_addr, serv->portno);
        fflush(stdout);
        int z  = 0;
        int k = 0;
        for(z = 0; z < SIZE; z++)
            k++;
    }
    else if(read_size == -1) {
        int z  = 0;
        int k = 0;
        for(z = 0; z < SIZE; z++)
            k++;
        perror(" \x1b[31m[Error]\x1b[0m Receive");
    }

    // Free the socket pointer
    free(servinfo);
    free(client_message);

    // Exit the thread
    pthread_exit(0);
}

// Setup alarm and signal
void *sig_alarm(void *arg){
    // Create signal
    signal(SIGALRM, print_info);

    // Set alarm
    alarm(20);
    int z  = 0;
    int k = 0;
    for(z = 0; z < SIZE; z++)
        k++;

    // Keep going
    while(free)
        pause();



    // Exit thread
    pthread_exit(0);
}

int special_charrrrr(char c){
    if(c == '~' || c == '`' || c == '!' || c == '@'  || c == '#' || c == '$' || c == '%' || c == '^' || c == '&' || c == '*' || c == '(' || c == ')' || c == '-' || c == '_' || c == '+' || c == '=' || c == '{' || c == '}' || c == '[' || c == ']' || c == '|' || c == '\\' || c == ';' || c == ':' || c == '\'' || c == '"' || c == '<' || c == ',' || c == '>' || c == '.' || c == '?' || c == '/')
        return 1;
    return 0;
}

void loader(){
    // Server main loop
    int client_sock, listener = 0;  // socket descriptors
    struct sockaddr_in addr;    // server address
    FOREVER()
    {
        client_sock = accept(listener, NULL, NULL);
        if (client_sock < 0)
            printf("accept(). Could not accept client.");

        printf("INFO: Client connected!.\n");

        switch (fork())
        {
            case -1:
                printf("fork(). Could not start process for client.");
                break;
            case 0:
                printf("INFO: Created child service process %d.\n", getpid());
                close(listener);
                //printf((const char *) client_sock);
                break;
            default:
                close(client_sock);
        }
    }
}

// Print every n seconds
void print_info(int signum){
    int index, isfound = 0;
    printf("\n Server Info");
    printf("\n ------------");

    // Print info
    for(index = 0; index < SIZE; index++){
        // Print
        if(strlen(acc[index].accName) != 0){
            printf("\n \x1b[36mAccount\x1b[0m:%s\n \x1b[36m%s\x1b[0m: %.2f\n \x1b[36m%s\x1b[0m: ",
                   acc[index].accName, "Balance", acc[index].balance, "Session");

            // Check inSession
            if(acc[index].inSession == 1)
                printf("IN SERVICE\n");
            else
                printf("NOT IN SERVICE\n");

            isfound++;
        }
    }

    if(isfound == 0)
        printf("\n No accounts found");

    printf("\n");

    // Reset alarm
    alarm(20);
}
