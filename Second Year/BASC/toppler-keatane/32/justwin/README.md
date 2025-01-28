# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: Just win  
**Cheat description**: you are just bored of the game, just deselect bonus flag, start the game and relax. Instant win.

## How to patch

The following explorations are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the start of the game _men\_main\_startgame\_proc_ and then forward to the _main\_game\_loop_. Here we can find the main handler of the level that is the _gam\_towergame_ that also set a lot of game states. In this function there are also some update functions, between the others _top_updatetoppler_ that handle the updates regarding the toppler (player).  
In this function there is a particular check that involves two things: the first is a check if the player is entering a door, and then if that door is the final (target) one. In this last case the responsible function is called _lev_is_targetdoor_ and will be the first one that will be patched.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
The first patch involves multiple overwrites of different lines of instructions with the objective of make the return line reduced to just return true, to make any door the final one. In order to do that I start to change the instructions just after the prologue, with several combinations to change the value in the EAX in order to get return true. So the first one is moving in EAX the zero value, but this would occupy more bytes, so I need to put a no-operation in the following byte. Then I try to change the following instructions to leave the EAX value unchanged, e.g. by adding zero.  
Finally I compare if the value of EAX is equal to zero, and that is decompiled just like returning always true, since the value of EAX does not change from the beginning where I set it to zero.

As results I get the first target door to be the final one.

But I'm a lazy player, so I don't want to even move the player. In order to do that I need to go the the caller function, that is _top_updatetoppler_, and in particular the check of the _up_down_ input that call the handling of the target door.  
In particular, before the calling of _lev_is_targetdoor_ and the check, there is another call to a different function which return value is used in AND with the check of the input.  
I could go further in _lev_is_door_upperend_ and try to return always true also there, but this time I want a different flow. So I directly try to avoid the calling of that function and also the entire check, so that the _lev_is_targetdoor_ function (that returns always true as patched before) is always called.

In order to achieve that, I change the entire call instruction with a mov in EAX register with a value 1, so that the test al, al (where al is the least significant byte of EAX and the test makes an AND) is always true, and EAX remains 1. At the very end I put a test al, 0x0, resulting in a direct jump into the function _lev_is_targetdoor_ that is decompiled as doing directly an 'else' in the control flow if no other action is taken. 

As result, start the game and enjoy the instant win in the towers.

NOTE: to make this script work and enjoying it, the game can't auto-win in transient scene with submarine; so bonus has to be kept deactivated.

