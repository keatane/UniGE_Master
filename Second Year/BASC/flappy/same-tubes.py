#!/usr/bin/env python3
import frida
import codecs
import sys

def on_message(message, data):
    if message['type'] == 'error':
        print("[!]", message['stack'])
    elif message['type'] == 'send':
        # print("[i]", message['payload'])
        print('f() called with n=%d' % message['payload'])
    else:
        print(message, data)

session = frida.attach('flappy')
script = session.create_script(r"""
'use strict';
/*
let modules = Process.enumerateModules();
modules.forEach(m=> {
console.log(m.name);
});
*/
let libc = Process.findModuleByName('libc.so.6');
/*
let exports = libc.enumerateExports();
exports.forEach(e=> {
console.log(e.name);
});
*/
let rand = libc.findExportByName('rand')
Interceptor.replace(rand, new NativeCallback(() => { return 0; }, 'int', []));
""")
script.on('message', on_message)
script.load()
sys.stdin.readline()
session.detach()
