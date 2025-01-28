// Author: Kevin Cattaneo - S4944382 - UniGE
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/wait.h>
#include <sys/user.h>
#include <inttypes.h>
#include <capstone/capstone.h>
#include <stdbool.h>
#include <errno.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "[X] - Usage: %s program [args]\n", argv[0]);
        return 1;
    }

    pid_t child_pid = fork();
    if (child_pid == 0) {
        ptrace(PTRACE_TRACEME, 0, 0, 0);
        execvp(argv[1], argv + 1); // to use environment variables, can specify them in PATH
        perror("[X] - execvp");
        exit(1);
    } else if (child_pid > 0) {
	int status;
	long instruction_count = 0;
	long count; // number of instructions disassembled by capstone
	bool disassembled = false;
	struct user_regs_struct regs;
	csh handle;
	cs_insn *insn;
	
	if (cs_open(CS_ARCH_X86, CS_MODE_64, &handle) != CS_ERR_OK){
		    return -1;
	}
	   
        while (waitpid(child_pid, &status, 0) == child_pid) {
            if (WIFEXITED(status)){
		break;
            }

            if (WIFSIGNALED(status)) {
                fprintf(stderr, "[X] - Child process terminated by signal %d\n", WTERMSIG(status));
                return 1;
            }

	    if (ptrace(PTRACE_GETREGS, child_pid, 0, &regs)) {
		perror("[X] - PTRACE_GETREGS");
		return -1;
	    }
	    
	    errno = 0;
	    long ins1 = ptrace(PTRACE_PEEKDATA, child_pid, regs.rip, 0);
	    long ins2 = ptrace(PTRACE_PEEKDATA, child_pid, regs.rip + sizeof(long), 0);
	    long ins[] = {ins1, ins2}; // max length of x86-64 instruction is 15 bytes
	    uint8_t* instruction = (uint8_t*)ins; // cast needed by capstone function
	    
	    if (errno) {
		    perror("[X] - PTRACE_PEEKDATA");
		    return -1;
	    }

	    count = cs_disasm(handle, instruction, sizeof(ins)-1, regs.rip, 0, &insn);
	    if (count > 0){
		    disassembled = true;
		    printf("0x%llx:%s  %s\n", regs.rip, insn->mnemonic, insn->op_str);
		    cs_free(insn, count);
		    instruction_count++;
	    } else {
	    	printf("[X] - ERROR: Failed to disassemble given instruction!\n");
	    }
	    ptrace(PTRACE_SINGLESTEP, child_pid, 0, 0);
	}
	cs_close(&handle);
	if (disassembled) // if any instruction has been disassembled
		printf("[V] - Child exited; n_instructions=%ld\n", instruction_count);
	else
	    	printf("[X] - ERROR: Failed to disassemble given program!\n");
        return 0;
    } else {
        perror("[X] - fork");
        return 1;
    }
}
