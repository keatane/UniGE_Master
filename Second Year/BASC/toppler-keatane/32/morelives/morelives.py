from pwn import *
import os

filename = "morelives"
elf = ELF("toppler32")
elf.write(0x08053caa, p8(0x60))
elf.save(filename)
os.chmod(filename, 0o755)
