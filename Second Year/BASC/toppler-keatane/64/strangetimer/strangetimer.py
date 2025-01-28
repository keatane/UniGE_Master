from pwn import *
import os

filename = "strangetimer64"
elf = ELF("toppler64")
elf.write(0x00406400, p8(0x1))
elf.save(filename)
os.chmod(filename, 0o755)
