# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: Just win  
**Cheat description**: you are just bored of the game, just deselect bonus flag, start the game and relax. Instant win.

## How to patch

_[ 64 bit: several steps in the patch has changed, described as follows ]_

The following explorations are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the start of the game _men\_main\_startgame\_proc_ and then forward to the _main\_game\_loop_. Here we can find the main handler of the level that is the _gam\_towergame_ that also set a lot of game states. In this function there are also some update functions, between the others _top_updatetoppler_ that handle the updates regarding the toppler (player).  
In this function there is a particular check that involves two things: the first is a check if the player is entering a door, and then if that door is the final (target) one. In this last case the responsible function is called _lev_is_targetdoor_ and will be the first one that will be patched.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
In the 64 bit version, the patch aims to change the comparison of AL with 0x12 with cmp al, al. So if the value of EAX is equal to zero, that is the result obtained, that is decompiled just like returning always true.

As results I get the first target door to be the final one.

But I'm a lazy player, so I don't want to even move the player. In order to do that I need to go the the caller function, that is _top_updatetoppler_, and in particular I see that before the call of _lev_is_targetdoor_ there are some checks on the result of _lev_is_door_upperend_. I decided to ignore whatever the result of that function is, so I change the instruction so that the checks are bypassed and so the _lev_is_targetdoor_ that returns always true for targetdoor is always scheduled.

As result, start the game and enjoy the instant win in the towers.

NOTE: to make this script work and enjoying it, the game can't auto-win in transient scene with submarine; so bonus has to be kept deactivated.

