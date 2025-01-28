from pwn import *
import os

filename = "hydrophobic64"
elf = ELF("toppler64")
elf.write(0x0041d21d, p8(0x40))
elf.write(0x0041d232, p8(0xff))
elf.save(filename)
os.chmod(filename, 0o755)
