#ifndef __PARSER_H__
#define __PARSER_H__

#include "data_types.h"

/**
 * Method to parse file/directory and index all found content
 * @arg name  - name of file/directory
 * @arg list  - list to store parsed data
 */
void parse_dir(const char * name, mapped_list_t * list);

#endif /* __PARSER_H__ */