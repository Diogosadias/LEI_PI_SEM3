INCLUDES = main.h calculate_energy.h is_refrigerated.h
SOURCES = main.c calculate_energy.c is_refrigerated.s
OBJFILES = main.o calculate_energy.o is_refrigerated.o
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
