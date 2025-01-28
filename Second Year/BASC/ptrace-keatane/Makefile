INSTRUCTION_OUT=instruction_counter
DISASM_OUT=dynamic_disasm
CCFLAGS=-m64
LIBNAME=capstone

$(INSTRUCTION_OUT): instruction_counter.o
	${CC} $< $(CCFLAGS) -o $@

$(DISASM_OUT): dynamic_disasm.o
	${CC} $< $(CCFLAGS) -Wall -l$(LIBNAME) -o $@

%.o: %.c
	${CC} -c $< -o $@

all: $(INSTRUCTION_OUT) $(DISASM_OUT)

.PHONY: clean
clean: 
	@rm -f $(INSTRUCTION_OUT) $(DISASM_OUT) *.o
