from pwn import *
import os

filename = "justwin"
elf = ELF("toppler32")
# lev_is_targetdoor
elf.write(0x0804f201, asm("mov eax, 0x0"))
elf.write(0x0804f206, asm("nop"))
elf.write(0x0804f207, asm("mov eax, 0x0"))
elf.write(0x0804f20c, asm("add eax, 0x0"))
elf.write(0x0804f20f, asm("cmp eax, 0x0"))

# top_updatetoppler
elf.write(0x0805c2a4, asm("mov eax, 0x1"))
elf.write(0x0805c2b0, asm("test al, al"))
elf.write(0x0805c2b2, asm("test al, 0x0"))

# to update game speed (see speedup hack)
elf.write(0x08053bf6, p8(0x10))

elf.save(filename)
os.chmod(filename, 0o755)
