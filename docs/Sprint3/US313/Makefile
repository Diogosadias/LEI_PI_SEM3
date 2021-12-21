SOURCES:=$(wildcard *.s *.c) 
 
OBJS1:=$(subst .c,.o,$(SOURCES)) 
OBJS:=$(subst .s,.o,$(OBJS1)) 


.c.o: 
	gcc -Wall -g -c $<  

.s.o: 
	gcc -Wall -g -c $<  


prog:  $(OBJS)  
	gcc -Wall -g $(OBJS)  -o prog 


run: prog 
	./prog 

clean:
	rm -f prog *.o  

vg:  prog 
	valgrind ./prog 

debug: prog 
	gdb -tui ./prog  