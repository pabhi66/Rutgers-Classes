COMPILER = gcc
CCFLAGS  = -Wall -ansi -pedantic
all: third

third: third.o
	$(COMPILER) $(CCFLAGS) -o third third.o
third.o: third.c
	$(COMPILER) $(CCFLAGS) -c third.c 
clean:
	rm -f third third.o