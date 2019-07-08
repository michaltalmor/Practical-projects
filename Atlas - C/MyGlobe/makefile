myGlobe: keyValuePair.o HashTable.o LinkedList.o Countries.o main.o
	gcc keyValuePair.o HashTable.o LinkedList.o Countries.o main.o -o myGlobe
keyValuePair.o: keyValuePair.c keyValuePair.h Defs.h
	gcc -c keyValuePair.c
HashTable.o: HashTable.c keyValuePair.h LinkedList.h
	gcc -c HashTable.c
LinkedList.o: LinkedList.c LinkedList.h Defs.h
	gcc -c LinkedList.c
Countries.o: Countries.c Countries.h Defs.h
	gcc -c Countries.c
main.o: main.c Countries.h HashTable.h keyValuePair.h LinkedList.h
	gcc -c main.c
