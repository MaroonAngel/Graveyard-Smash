package net.jam.gravesmash.lib;

import net.minecraft.util.Util;
import net.minecraft.util.math.Direction;

import java.util.Random;

public class Directions {
    public static Direction[] dirs;

    public static void register() {
        dirs = new Direction[4];
        dirs[0] = Direction.NORTH;
        dirs[1] = Direction.EAST;
        dirs[2] = Direction.WEST;
        dirs[3] = Direction.SOUTH;
    }

    public static Direction random(Random random) {
        return Util.getRandom(dirs, random);
    }

}
