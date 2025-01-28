// Author: Kevin Cattaneo - S4944382 - UniGE
#include <stdio.h>
#include <stdlib.h>
#include <unistd.h>
#include <sys/ptrace.h>
#include <sys/wait.h>

int main(int argc, char *argv[]) {
    if (argc < 2) {
        fprintf(stderr, "[X] - Usage: %s program [args]\n", argv[0]);
	return 1;
    }

    pid_t child_pid = fork();
    if (child_pid == 0) {
        ptrace(PTRACE_TRACEME, 0, 0, 0);
        execvp(argv[1], argv + 1);
        perror("[X] - execvp");
        exit(1);
    } else if (child_pid > 0) {
        int status;
        long instruction_count = 0;
	
	printf("[-] - Tracing %s...\n\n", argv[1]);
        while (waitpid(child_pid, &status, 0) == child_pid) {
            if (WIFEXITED(status)){
		break;
            }

            if (WIFSIGNALED(status)) {
                fprintf(stderr, "[X] - Child process terminated by signal %d\n", WTERMSIG(status));
                return 1;
            }
		
            instruction_count++;
	    ptrace(PTRACE_SINGLESTEP, child_pid, 0, 0);
        }

        printf("\n[V] - Child exited; n_instructions=%ld\n", instruction_count);
        return 0;
    } else {
        perror("[X] - fork");
        return 1;
    }
}
