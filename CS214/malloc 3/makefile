all:
	gcc -c main.c
	gcc -c mymalloc.c
	ar rc libmym.a mymalloc.o
	gcc -o malloc main.c libmym.a

clean:
	rm -rf *.o
	rm -rf libmym.a