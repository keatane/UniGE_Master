# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: Speedup  
**Cheat description**: the overall game speed is increased  


## How to patch

The following explorations are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the game option menu _men\_options_ and then _men\_game\_options\_menu_ where I can find the _game\_options\_menu\_speed_ function that handle the game speed.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
After positioning on the line that reset the game speed to maximum 3 if I try to further increase it, I change the 3 value with 0x10, so 16. In this way when changing the game speed, if it goes past 3 (so three times right), the game speed goes up to maximum 16.  

As result, the game option is saved and the game can be played with this amount of speed.

NOTE: the change in game speed applies when set in the game main menu, before starting the game.
