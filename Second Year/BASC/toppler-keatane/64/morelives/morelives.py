from pwn import *
import os

filename = "morelives64"
elf = ELF("toppler64")
elf.write(0x0040f7c7, p8(0x60))
elf.save(filename)
os.chmod(filename, 0o755)
