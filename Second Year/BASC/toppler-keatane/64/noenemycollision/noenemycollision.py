from pwn import *
import os

filename = "noenemycollision64"
elf = ELF("toppler64")
elf.write(0x00414a51, p8(0x40))
elf.save(filename)
os.chmod(filename, 0o755)
