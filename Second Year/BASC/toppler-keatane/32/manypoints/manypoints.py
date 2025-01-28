from pwn import *
import os

filename = "manypoints"
elf = ELF("toppler32")
elf.write(0x0804c57c, p8(0x7f))
elf.save(filename)
os.chmod(filename, 0o755)
