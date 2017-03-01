#include "data_types.h"
#include <string.h>
#include <stdio.h>

#define INITIAL_CAPACITY 10

/* comparator function for qsort*/
static int list_record_comparator(const void * a, const void * b)
{
	const list_record_t * f = (list_record_t *)a;
	const list_record_t * s = (list_record_t *)b;
	return strcmp(f->word, s->word);
}

/* comparator function for qsort*/
static int pairs_comparator(const void * a, const void * b)
{
	const index_pair_t * f = (index_pair_t *)a;
	const index_pair_t * s = (index_pair_t *)b;
	
	if (f->count > s->count)
		return -1;
	else if (f->count < s->count)
		return 1;

	return strcmp(f->file, s->file);
}

void init_pairs_list(pairs_list_t * list)
{
	list->capacity = INITIAL_CAPACITY;
	list->pairs = malloc( sizeof(index_pair_t) * list->capacity );
	list->size = 0;
}

void init_mapped_list(mapped_list_t * list)
{
	list->capacity = INITIAL_CAPACITY;
	list->records = malloc( sizeof(list_record_t) * list->capacity );
	list->size = 0;
}

index_pair_t create_pair(const char * word, int count)
{
	index_pair_t pair;
	pair.file = malloc( sizeof(char) * ( strlen(word) + 1) );
	strcpy(pair.file, word);
	pair.count = count;
	return pair;
}

list_record_t create_record(const char * file, const char * word, int count)
{
	list_record_t record;
	record.word = malloc( sizeof(char) * (strlen(word)  +1) );
	strcpy(record.word, word);
	init_pairs_list(&record.list);
	add_pair(&record.list, file, count);
	return record;
}

void add_pair(pairs_list_t * list, const char * file, int count)
{
	int i;
	for(i=0; i< list->size; ++i)
	{
		if(strcmp(list->pairs[i].file, file) == 0)
		{
			list->pairs[i].count += count;
			return;
		}
	}
	if(list->size + 1 > list->capacity)
	{
		list->capacity *= 2;
		list->pairs = realloc(list->pairs, sizeof(index_pair_t) * list->capacity);
	}
	list->pairs[list->size] = create_pair(file, count);
	list->size += 1;
}

void add_list_record(mapped_list_t * list, const char * file, const char * word, int count)
{
	int i;
	for(i=0; i< list->size; ++i)
	{
		if(strcmp(list->records[i].word, word) == 0)
		{
			add_pair(&list->records[i].list, file, count);
			return;
		}
	}
	if(list->size + 1 > list->capacity)
	{
		list->capacity *= 2;
		list->records = realloc(list->records, sizeof(list_record_t) * list->capacity);
	}
	list->records[list->size] = create_record(file, word, count);
	list->size += 1;
}

void sort_mapped_list(mapped_list_t * list)
{
	int i, j;
	qsort(list->records, list->size, sizeof(list_record_t), list_record_comparator);
	for (i = 0; i < list->size; ++i)
	{
		for (j = 0; j < list->records[i].list.size; ++j)
			qsort(list->records[i].list.pairs, list->records[i].list.size, sizeof(index_pair_t), pairs_comparator);
	}
}

void list2json(mapped_list_t * list, const char * file)
{
	FILE * fout = fopen(file, "w");
	int i;
	int j;
	if (!fout)
	{
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

void destroy_list(mapped_list_t * list)
{
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