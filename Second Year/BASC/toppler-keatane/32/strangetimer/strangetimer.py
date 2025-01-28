from pwn import *
import os

filename = "strangetimer"
elf = ELF("toppler32")
elf.write(0x0804c4f1, asm("add eax, 0x1"))
elf.save(filename)
os.chmod(filename, 0o755)
