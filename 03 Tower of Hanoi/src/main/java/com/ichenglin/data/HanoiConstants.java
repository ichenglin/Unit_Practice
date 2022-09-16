package com.ichenglin.data;

import com.ichenglin.utility.Graphics;

public final class HanoiConstants {

    private HanoiConstants() {};

    public static final byte HANOI_TOWER_TOWERS = 3;
    public static final byte[][] HANOI_DISK_GRADIENT = new byte[][] {
            {(byte) 128, (byte) 0, (byte) 0},
            {(byte) 255, (byte) 255, (byte) 0},
            {(byte) 0, (byte) 255, (byte) 0},
            {(byte) 0, (byte) 0, (byte) 255}
    };

}
