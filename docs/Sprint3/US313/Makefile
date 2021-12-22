#MakeFile

all: run clean

run: EXsprint3
		./EXsprint3
clean:
		rm -f *.o EXsprint3
	
EXsprint3: main.o calculate_slots.o
		gcc -Wall main.o calculate_slots.o -o EXsprint3
		
main.o: main.c
		gcc -g -Wall -c main.c -o main.o
		
calculate_slots.o: calculate_slots.s
		gcc -g -Wall -c calculate_slots.s -o calculate_slots.o

