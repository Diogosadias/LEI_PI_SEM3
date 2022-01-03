INCLUDES = calculate_slots.h there_is_container.h slots_occupied.h
SOURCES = calculate_slots.s there_is_container.s slots_occupied.s main.c
OBJFILES = calculate_slots.o there_is_container.o slots_occupied.o main.o
EXEC = prog

.SUFIXES : .c .o .s

.c.o:
	gcc -g -Wall -c $<

${EXEC}: ${OBJFILES}
	gcc -g -Wall -o ${EXEC} ${OBJFILES}

${OBJFILES}: ${SOURCES} ${INCLUDES}

run: ${EXEC}
	./${EXEC}

clean:
	rm -f ${OBJFILES} ${EXEC}