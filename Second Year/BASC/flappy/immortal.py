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

let flappy = Process.findModuleByName('flappy');
let exports = flappy.enumerateSymbols();
/*
exports.forEach(e=> {
console.log(e.name);
});
*/

//let game_over = flappy.findSymbolByName('game_over')
let game_over = exports[96];
Interceptor.replace(game_over.address, new NativeCallback(() => { return 0; }, 'int', []));
""")
script.on('message', on_message)
script.load()
sys.stdin.readline()
session.detach()
