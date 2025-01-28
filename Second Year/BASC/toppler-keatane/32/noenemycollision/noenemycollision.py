from pwn import *
import os

filename = "noenemycollision"
elf = ELF("toppler32")
elf.write(0x080568f8, p8(0x40))
elf.save(filename)
os.chmod(filename, 0o755)
