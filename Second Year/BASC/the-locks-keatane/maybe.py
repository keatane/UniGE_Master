def decrypt(buf, bufsize):
    enc_data = bytearray(b & 0xff for b in getBytes(toAddr(buf), bufsize))
    key = ord(')') 
    result = bytearray(byte + key for byte in enc_data)
    return result

dec_data = decrypt(0x0002afd0, 0xc)
print(dec_data.decode('latin1'))
