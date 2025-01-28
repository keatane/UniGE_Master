#!/usr/bin/env python3
# -*- coding: utf-8 -*-
# This exploit template was generated via:
# $ pwn template ./keatane-the_lock-level_1 --host remotehost --port 1234
from pwn import *

# Set up pwntools for the correct architecture
exe = context.binary = ELF(args.EXE or './keatane-the_lock-level_1')

# Many built-in settings can be controlled on the command-line and show up
# in "args".  For example, to dump all data sent/received, and disable ASLR
# for all created processes...
# ./exploit.py DEBUG NOASLR
# ./exploit.py GDB HOST=example.com PORT=4141 EXE=/tmp/executable
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
tbreak main
continue
'''.format(**locals())

#===========================================================
#                    EXPLOIT GOES HERE
#===========================================================
# Arch:     i386-32-little
# RELRO:      Partial RELRO
# Stack:      No canary found
# NX:         NX enabled
# PIE:        PIE enabled
# Stripped:   No
# Debuginfo:  Yes

# I re-implement the decode function seen in Ghidra
def decrypt(buf, bufsize):
    enc_data = bytearray(b for b in exe.read(buf, bufsize))
    key = ord(')') 
    result = bytearray(byte + key for byte in enc_data)
    return result

dec_data = decrypt(exe.symbols['my_super_password'],0xc) # with symbols
#dec_data = decrypt(0x0001afd0, 0xc) # without symbols
print(f"Password: {dec_data.decode('latin1')}\n")

# Then I start the program just to obtain the flag back
io = start()
io.recvuntil(b'too late...')
io.sendline(dec_data)
io.interactive()

