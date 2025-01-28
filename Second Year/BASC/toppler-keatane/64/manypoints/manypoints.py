from pwn import *
import os

filename = "manypoints64"
elf = ELF("toppler64")
elf.write(0x00405c20, p32(0xbadc0ffe))
elf.save(filename)
os.chmod(filename, 0o755)
