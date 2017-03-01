#ifndef __DATA_TYPES__
#define __DATA_TYPES__

#include <stdlib.h>

/**
 * Data type to represent pair: <file_name> : <number_of_occurrences>
 * @sample: [ "file_name" : <occurrences> ]
 */
typedef struct 
{
	char * file;
	int count;
} index_pair_t;

/**
 * Utility data type to store index_pairs as array
 */
typedef struct
{
	index_pair_t * pairs;
	int size;
	int capacity;
} pairs_list_t;

/**
 * Data type to represent pair: <word> : <list_of_pair_index>
 * @sample: {  "word":  [ "file_name" : <occurrences>, ...] }
 */
typedef struct
{
	char * word;
	pairs_list_t list;
} list_record_t;

/**
 * Utility data type to store all the word with its occurrences
 */
typedef struct
{
	list_record_t * records;
   	int size;
	int capacity;
} mapped_list_t;

/************************ IMPLEMENTATION ************************/
/**
 *  Method to create index pair, dynamically allocates memory for file field
 *  @arg file    -    name of parsed file
 *  @arg count   -    number of some word occurrences
 */
index_pair_t create_pair(const char * file, int count);

/**
 *  Method to create index pair with word, dynamically allocates memory for word field
 *  @arg file    -    name of parsed file
 *  @arg word    -    name of searched word
 *  @arg count   -    number of word occurrences
 */
list_record_t create_record(const char * file, const char * word, int count);

/**
 *  Utility method to init pair array
 *  @arg list    -    list to be initialized
 */
void init_pairs_list(pairs_list_t * list);

/**
 *  Utility method to init mapped array
 *  @arg list    -    list to be initialized
 */
void init_mapped_list(mapped_list_t * list);

/**
 *  Method to add new pair to the pair list or alter existent
 *  @arg list    -    list to be modified
 *  @arg file    -    name of parsed file
 *  @arg count   -    number of some word occurrences
 */
void add_pair(pairs_list_t * list, const char * file, int count);

/**
 *  Method to add new pair of <word> : <pair+list> to the mapped list
 *  @arg list    -    list to be modified
 *  @arg file    -    name of parsed file
 *  @arg word    -    name searched word
 *  @arg count   -    number of some word occurrences
 */
void add_list_record(mapped_list_t * list, const char * file, const char * word, int count);

/**
 *  Method to sort data in the created list
 *  @arg list    -    list to be sorted
 */
void sort_mapped_list(mapped_list_t * list);

/**
 *  Method to create json file with parsed data
 *  @arg list    -    list with data
 *  @arg file    -    name of file to be created
 */
void list2json(mapped_list_t * list, const char * file);

/**
 *  Method to free all allocated dynamic memory
 *  @arg list    -    list to be sorted
 */
void destroy_list(mapped_list_t * list);

#endif /*__DATA_TYPES__*/
