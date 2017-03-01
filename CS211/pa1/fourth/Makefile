COMPILER = gcc
CCFLAGS  = -Wall -ansi -pedantic
all: fourth

fourth: fourth.o
	$(COMPILER) $(CCFLAGS) -o fourth fourth.o
fourth.o: fourth.c
	$(COMPILER) $(CCFLAGS) -c fourth.c 
clean:
	rm -f fourth fourth.o