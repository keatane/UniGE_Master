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

io = start()

# receive and parse the secure password address
p = process('./keatane-the_lock-level_1')
p.recvuntil(b'(this time it is \x1b[0;35m')
address = p.recvuntil(b')')
address = address.strip()
address = address.split(b'\x1b')[0]
password_address = int(address, 16)
print(hex(password_address))

gdb_output = ''
gdb.attach(p, gdbscript='''
     print (char*){hex(password_address)}
     quit    
''')

gdb_output = p.recvline()

print("GDB Output:")
print(gdb_output)
clean_string = re.sub(r'\x1b\[[0-9;]*m', '', gdb_output.decode('utf-8')).strip()
print(clean_string)

#io.recvuntil(b'password: ')
#io.sendline(b'' + clean_string)
io.interactive()
