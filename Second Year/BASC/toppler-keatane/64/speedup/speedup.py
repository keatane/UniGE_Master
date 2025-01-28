from pwn import *
import os

filename = "speedup64"
elf = ELF("toppler64")
elf.write(0x0040f8fd, p8(0x10))
elf.save(filename)
os.chmod(filename, 0o755)
