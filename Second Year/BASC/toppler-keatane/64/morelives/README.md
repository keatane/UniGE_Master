# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: More lives  
**Cheat description**: the max number of lives is increased of about 100  


## How to patch

_[ 64 bit: the patch is the same, but the number of lives is not updated directly on the config data struct; it is put as argument in the ESI register and a function is called to change it ]_

The following explorations are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the game option menu _men\_options_ and then _men\_game\_options\_menu_ where I can find the _game\_options\_menu\_lives_ function that handle the player max lives up to 3.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
After positioning on the line that reset the player lives to maximum 3 if I try to further increase it, I change the 3 value with 0x60, so 96, packing a single byte. In this way when changing the player lives, if it goes past 3 (so three times right), a lot of images of creatures spawn on the display, so much that can't be counted.  

As result, if I proceed back and then start the game, an amount of 96 lives is displayed.

