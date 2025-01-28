from pwn import *
import os

filename = "hydrophobic"
elf = ELF("toppler32")
elf.write(0x0805bfc0, p8(0x40))
elf.write(0x0805bfd4, p8(0xff))
elf.save(filename)
os.chmod(filename, 0o755)
