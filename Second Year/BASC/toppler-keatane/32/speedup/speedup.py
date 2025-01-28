from pwn import *
import os

filename = "speedup"
elf = ELF("toppler32")
elf.write(0x08053bf6, p8(0x10))
elf.save(filename)
os.chmod(filename, 0o755)
