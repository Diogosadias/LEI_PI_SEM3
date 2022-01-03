.section .data
.global positionsMax

.section .text
.global slots_occupied

slots_occupied:

    # 1st edi, 2nd esi, 3rd edx, 4th ecx

    movl $0, %eax # inicializar o retorno a 0
    movq %rdi, %r8 # mover o endereço do array para %r8
    movl positionsMax(%rip), %ecx # inicializar %ecx, contador, com o número de elementos do array

loop:

    cmpl $0, %ecx # verificar se o número de elementos é 0
    jz end # caso seja 0, saltar para end
    
    movl (%r8), %edi # mover o apontado por %r8 (valor de x) para %edi
    addq $4, %r8 # incrementar o apontador para o proximo valor
    movl (%r8), %esi # mover o apontado por %r8 (valor de y) para %esi
    addq $4, %r8 # incrementar o apontador para o proximo valor
    movl (%r8), %edx # mover o apontado por %r8 (valor de z) para %edx

    pushq %rcx # caller saves %ecx before call
    pushq %r8 # caller saves %r8 before call
    pushq %rax # caller saves %eax before call
    
    call there_is_container # chamar função da US315

    cmpl $0, %eax # comparar se %eax é 0 
    je doesnt_exist # caso seja, saltar para doesnt_exist
    jmp exists # caso não seja, saltar para exists

doesnt_exist:

    popq %rax # caller restores %eax after test_even return
    popq %r8 # caller restores %r8 after test_even return
    popq %rcx # caller restores %ecx after test_even return

    addq $4, %r8 # incrementar %r8d, apontador do array
    subl $3, %ecx # decrementar %ecx, número de elementos
    jmp loop # voltar a iteração

exists:

    popq %rax # caller restores %eax after test_even return
    popq %r8 # caller restores %r8 after test_even return
    popq %rcx # caller restores %ecx after test_even return

    incl %eax # eax = eax + 1
    addq $4, %r8 # incrementar %r8d, apontador do array
    subl $3, %ecx # decrementar %ecx, número de elementos
    jmp loop # voltar a iteração

end:

    ret # return to the caller function
