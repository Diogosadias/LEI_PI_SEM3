.section .data
.global size

.equ coords_size, 4
.equ struct_size, 104
.equ minus_x_size, 100
.equ minus_x_y_size, 96
.equ refr_minus_coord_size, 52

.section .text
.global is_refrigerated

is_refrigerated:

#1 parametro no %rdi, 2 parametro no %esi, 3 parametro no %edx, 4 parametro no %ecx


addq $coords_size, %rdi #rdi está a apontar para x
movl $-1, %eax #se retornar -1 significa que não existe contentor naquela posição
movl $0, %r10d #counter
movl size(%rip), %r8d

myloop:

cmpl %r8d, %r10d #compara o contador de instancias da struct com o total de instancias
je end

movl (%rdi), %r9d # copy x from the struct (pointed by %rdi) to %r9d
cmpl %r9d, %esi #checks if the x is equal
je equal_x

addq $struct_size, %rdi #move para a nova instancia de x na struct
incl %r10d

jmp myloop

equal_x:

addq $coords_size, %rdi #rdi está a apontar para y
movl (%rdi), %r9d # copy y from the struct (pointed by %rdi) to %r9d
cmpl %r9d, %edx #checks if the y is equal
je equal_y

addq $minus_x_size, %rdi #move para a nova instancia de x na struct
incl %r10d
jmp myloop

equal_y:

addq $coords_size, %rdi #rdi está a apontar para z
movl (%rdi), %r9d # copy z from the struct (pointed by %rdi) to %r9d
cmpl %r9d, %ecx #checks if the z is equal
je equal_z

addq $minus_x_y_size, %rdi #move para a nova instancia de x na struct
incl %r10d
jmp myloop


equal_z:

addq $refr_minus_coord_size, %rdi #o apontador está na variável isItRefrigerated
movl (%rdi), %r9d # copy isItRefrigerated from the struct (pointed by %rdi) to %r9d
cmpl $1, %r9d #checks if the container is refrigerated
je refrigerated

movl $0, %eax #se não for refrigerado retorna 0
jmp end


refrigerated:
movl $1, %eax #se for refrigerado retorna 1
jmp end

end:
ret
