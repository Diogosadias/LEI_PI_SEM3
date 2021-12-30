
.section .data
.global vec

.section .text
.global there_is_container

there_is_container:
# 1 - rdi  ..... x
# 2 - rsi ...... y
# 3 - rdx ...... z

leaq vec(%rip), %r10 # pointer

# fromula  => Vec[x][y][z] = vec + (x * X_Max_Size * Y_Max_Size + y * Y_Max_Size + z) * k          
imulq $20, %rdi; # x * X_Max_Size 

imulq $21, %rdi; # x * Y_Max_Size 

imulq $21, %rsi # y * Y_Max_Size

addq %rsi, %rdi; # (x * X_Max_Size * Y_Max_Size) + (y * Y_Max_Size)                                 

addq %rdx, %rdi; # (x * X_Max_Size * Y_Max_Size) + (y * Y_Max_Size) + z

imulq $4, %rdi # (x * X_Max_Size* Y_Max_Size + y * Y_Max_Size + z) * k 

addq %rdi, %r10 # vec + (x * X_Max_Size * Y_Max_Size + y * Y_Max_Size + z) * k 

movq (%r10), %rcx # Vec[x][y][z] = id_container

cmpq $0, %rcx 
je is_null # if id_container ==  0
movl $1, %eax
jmp end

is_null:
movl $0, %eax
jmp end



end:
ret
