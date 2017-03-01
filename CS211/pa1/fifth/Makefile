COMPILER = gcc
CCFLAGS  = -Wall -pedantic
all: fifth

fifth: fifth.o
	$(COMPILER) $(CCFLAGS) -o fifth fifth.o
fifth.o: fifth.c
	$(COMPILER) $(CCFLAGS) -c fifth.c 
clean:
	rm -f fifth fifth.o