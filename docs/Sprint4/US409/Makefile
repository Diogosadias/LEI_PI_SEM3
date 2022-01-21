INCLUDES = main.h
SOURCES = main.c
OBJFILES = main.o
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