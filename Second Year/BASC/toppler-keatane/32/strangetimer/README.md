# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: Strangetimer  
**Cheat description**: the time counter does not decrease anymore... it will increase instead!

## How to patch

The following explorations are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the start of the game _men\_main\_startgame\_proc_ and then forward to the _main\_game\_loop_. Here we can find the main handler of the level that is the _gam\_towergame_ that also set a lot of game states. Here there is a function that handle the time: _akt\_time_. In this function the time is updated directly like time = time - 1.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
The patch changes the instruction from a sub to an add, so that time increase instead of decrease.

Another way could be without changing the sub instruction, by subtract a negative number, so that minus times minus gives a positive value.

As result, when the level starts, the timer increase instead of decreasing.

