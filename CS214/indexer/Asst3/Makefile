CC = gcc

PROJECT_PATH = ./

CFLAGS 	 = -Wall -std=c99 -pedantic

#INCLUDES = -I $(PROJECT_PATH)/headers 

EXECUTABLE = index

C_SOURCES=$(wildcard $(PROJECT_PATH)/*.c)

#CPP_OBJECTS=$(CPP_SOURCES:.cpp=.o)

C_OBJECTS=$(C_SOURCES:.c=.o)

all: $(EXECUTABLE)

$(EXECUTABLE): $(C_OBJECTS)
		$(CC) $(CFLAGS) $(INCLUDES) -o $(EXECUTABLE) $(C_OBJECTS)

.c.o:
	$(CC) $(CFLAGS) $(INCLUDES) -c $<  -o $@
		
.cpp.o:
	$(CC) $(CFLAGS) $(INCLUDES) -c $< -o $@
	
clean: 
	$(RM) $(EXECUTABLE).exe *.o *~ $(EXECUTABLE)
	$(RM) $(PROJECT_PATH)/*.o