# Analysis Description

**Tools used**: Ghidra, pwntools  
**Cheat activated**: No enemy collision  
**Cheat description**: whenever the player collide with an enemy nothing happens.

## How to patch

_[ 64 bit: the patch is the same, just the target address changes ]_ 

The following explorations are done from the decompiler window.
Starting from the _men\_main_ function, that is the function that manage the main menu, I explored the function linked to the start of the game _men\_main\_startgame\_proc_ and then forward to the _main\_game\_loop_. Here we can find the main handler of the level that is the _gam\_towergame_ that also set a lot of game states. In this function there are also some update functions, in this case _top\_testcollision_, that allow to register any change on the collision, by going further into _rob_topplercollision_ I can manage the toppler (player) collision.

The patching part involve both the decompiler window to see the results of the changed instructions and the listing disassembled window to edit instructions.  
In the function there is a loop that exits when a certain variable is smaller or equal to 3. By changing that assignment with 4 or bigger, we return suddenly from the loop without registering any (enemy) collision.

As result, when the creature collides with enemies nothing happens: no damage nor knockback.

