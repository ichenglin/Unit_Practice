package com.ichenglin.data;

public final class HanoiConstants {

    private HanoiConstants() {};

    public static final byte HANOI_TOWER_TOWERS = 3;
    public static final byte[][] HANOI_DISK_GRADIENT = new byte[][] {
            {(byte) 255, (byte) 0, (byte) 0},
            {(byte) 255, (byte) 255, (byte) 0},
            {(byte) 0, (byte) 255, (byte) 0},
            {(byte) 0, (byte) 0, (byte) 255}
    };

    public static final byte[] HANOI_DISK_BLOCK_SELECTED_BACKGROUND = new byte[]{(byte) 255, (byte)255, (byte)255};
    public static final byte[] HANOI_DISK_BLOCK_SELECTED_FOREGROUND = new byte[]{(byte) 0, (byte)0, (byte)0};

}
