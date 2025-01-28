#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# This exploit template was generated via:
# $ pwn template ./keatane-the_lock-level_2 --host remotehost --port 1234
from pwn import *

# Set up pwntools for the correct architecture
exe = context.binary = ELF(args.EXE or './keatane-the_lock-level_2')

# Many built-in settings can be controlled on the command-line and show up
# in "args".  For example, to dump all data sent/received, and disable ASLR
# for all created processes...
host = args.HOST or 'remotehost'
port = int(args.PORT or 1234)


def start_local(argv=[], *a, **kw):
    '''Execute the target binary locally'''
    if args.GDB:
        return gdb.debug([exe.path] + argv, gdbscript=gdbscript, *a, **kw)
    else:
        return process([exe.path] + argv, *a, **kw)

def start_remote(argv=[], *a, **kw):
    '''Connect to the process on the remote host'''
    io = connect(host, port)
    if args.GDB:
        gdb.attach(io, gdbscript=gdbscript)
    return io

def start(argv=[], *a, **kw):
    '''Start the exploit against the target.'''
    if args.REMOTE:
        return start_remote(argv, *a, **kw)
    else:
        return start_local(argv, *a, **kw)

# Specify your GDB script here for debugging
# GDB will be launched if the exploit is run via e.g.
# ./exploit.py GDB
gdbscript = '''
continue
'''.format(**locals())

#===========================================================
#                    EXPLOIT GOES HERE
#===========================================================
# Arch:     amd64-64-little
# RELRO:      Partial RELRO
# Stack:      No canary found
# NX:         NX enabled
# PIE:        PIE enabled

from unicorn import Uc, UC_ARCH_X86, UC_MODE_64, UC_PROT_READ, UC_PROT_WRITE, UC_PROT_ALL
from unicorn.x86_const import UC_X86_REG_RSP, UC_X86_REG_RDI

emu = Uc(UC_ARCH_X86, UC_MODE_64)

CODE_ADDR = 0
CODE_SIZE = 0x00018000
STACK_ADDR = 1024*1024
STACK_SIZE = 1024*1024

all_bytes = bytes(b for b in exe.read(CODE_ADDR, CODE_SIZE))

PG_CODE_SIZE = (CODE_SIZE + 4095) & ~4095
emu.mem_map(CODE_ADDR, PG_CODE_SIZE, UC_PROT_ALL)
emu.mem_write(CODE_ADDR, all_bytes)
emu.mem_map(STACK_ADDR, STACK_SIZE, UC_PROT_READ|UC_PROT_WRITE)
emu.reg_write(UC_X86_REG_RSP, STACK_ADDR + STACK_SIZE)

# address of password seen on Ghidra
emu_enc_data = emu.mem_read(0x00017fd0,0x1c).decode('latin1') # just for print purposes

emu.reg_write(UC_X86_REG_RDI, 0x00017fd0)
emu.emu_start(0x000011f3, 0x00001498) # emulate the decode function

emu_dec_data = emu.mem_read(0x00017fd0,0x1c).decode('latin1')

print(f'Encoded Password: {emu_enc_data}\nDecoded Password: {emu_dec_data}\n\n')

io = start()
io.recvuntil(b'too late...')
io.sendline(emu_dec_data.encode())
io.interactive()
