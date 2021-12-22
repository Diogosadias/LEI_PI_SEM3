
.section .data
.global ptrVec
.global num

.comm ptrVec ,8 # declare pointer (8 bytes )

.section .text
.global calculate_slots

calculate_slots:
movq ptrVec (%rip), %rsi  #moves ptrvec to %rsi
movl num(%rip) , %ecx #moves num to %ecx
movq $0, %rax  #counter of free slots
movl $0, %r10d  #counter of occupied slots
movl $0, %r8d  #counter to be compared with the maximum numbers of the 3D vec

loop :

movl (%rsi), %edx #copy first int from vec (pointed by %rsi) to %edx
cmpl %ecx , %r8d #check if this is the end of the 3D vec
je end #jump if it is the end
cmpl $0, %edx #compares 0 with the value pointed by prtvec
je is_a_free_slot #jump to that cycle if the value pointed by ptrvec is equal to zero

incl %r10d #if doesnt jump to the previous cycle that means it is a occupied slot, so increases that counter
addq $4 , %rsi #move to the next int in vec
incl %r8d #%r8d++
jmp loop #next iteration


is_a_free_slot:

incq %rax #increases the counter of the free slots
addq $4 , %rsi #move to the next int in vec
incl %r8d #%r8d++
jmp loop #next iteration

end:
rclq $32, %r10 #rotate the %r10 register which makes %r10d the 4 most significant bytes of %r10
shrd $32, %rax, %r10 #takes the 4 less significant bytes (32 bits) from %rax and puts them on the most 4 significant bytes of %r10, so that means that the previous 4 most significant bytes from the last operation, now are the 4 less significant bytes of the %r10 register
movq %r10, %rax #moves the %r10 (which already has the occupied slots on the 4 less significant bytes and the free slots on the 4 most significant bytes) on the %rax (return register)

ret
