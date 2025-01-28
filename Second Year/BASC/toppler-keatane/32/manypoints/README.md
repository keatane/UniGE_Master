# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: Manypoints  
**Cheat description**: whenever the player reaches a new high, it receives tons of points!

## How to patch

The following explorations are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the start of the game _men\_main\_startgame\_proc_ and then forward to the _main\_game\_loop_. Here we can find the main handler of the level that is the _gam\_towergame_ that also set a lot of game states. Here there is a function that checks if a _new\_height_ is reached, and if so the amount of points is increased by calling _pts\_add_.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
The patch changes the argument number passed to _pts\_add_, increasing the value. 

As result, when the creature reaches a new highest vertical position, the amount of points provided is increased by a lot.

