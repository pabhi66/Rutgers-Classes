#include "read_file.h"
#include <sys/stat.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>

int file_exists(const char * file);

int main(int argc, char ** argv)
{
	mapped_list_t list;	
    /* check if all arguments were provided */
	if (argc != 3)
	{
		printf("Invalid command line arguments.\nUsage: <./index> <inverted-index file name> <directory or file name>\n");
		exit(EXIT_FAILURE);
	}
    /*  if file exists ask for permission for overriding */
	if (file_exists(argv[1]))
	{
		printf("%s File already exists, do you want to override it? enter 'y' for yes and 'n' for no\n", argv[1]);
		printf("\tInput Answer > ");
		fflush(stdin);
		if (  (char)tolower(  (char)getc(stdin) ) != 'y')
			exit(EXIT_SUCCESS);
	}
	/* initialization */
	init_mapped_list(&list);
	/* parsing provided file / directory */
	parse_dir(argv[2], &list);
	/* sorting results according to the specification */
	sort_mapped_list(&list);
	/* writing results to json format */
	list2json(&list, argv[1]);
	/* release allocated memory */
	destroy_list(&list);
	return 0;
}

int file_exists(const char * file) 
{
	struct stat st;
	int result = stat(file, &st);
	return result == 0;
}
