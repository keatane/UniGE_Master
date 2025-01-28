# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: Hydrophoby  
**Cheat description**: when the player touches water it projects in air like a rocket, reaching the top of the tower  


## How to patch

_[64 bit: the patch is the same, just the target address change, obtainable by exploring the call graph in comparison with the 32 bit one]_

The following exploration are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the start of the game _men\_main\_startgame\_proc_ and then forward to the _main\_game\_loop_. Here we can find the main handler of the level that is the _gam\_towergame_ that also set a lot of game states. Going into _top\_updatetoppler_ I see the _move\_toppler_. At this point I knew that the "toppler" must have been the creature, also because left\_right input were passed to the function. In the _move\_toppler_ there was a particular function name _drown_; as it suggests it control the behavior when the toppler touch water, that is when the player goes below a certain vertical position.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
The first patch was to change the state when touching water with something different from 8, that is the dead state. So one possible solution is the 4.  
Then I set the new vertical position to 0xff.

As result, when the creature touches water, it goes up like a rocket to the very upper part of the tower.

NOTE: this script alone is not very effective, it is suggested to combine it with the no enemy collision cheat.
