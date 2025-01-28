from pwn import *
import os

filename = "justwin64"
elf = ELF("toppler64")

# lev_is_targetdoor
elf.write(0x004095ae, asm("cmp al, al"))

# top_updatetoppler
elf.write(0x0041d5eb, p8(0x1))

# to update speed from options (see speedup hack)
elf.write(0x0040f8fd, p8(0x10))

elf.save(filename)
os.chmod(filename, 0o755)
