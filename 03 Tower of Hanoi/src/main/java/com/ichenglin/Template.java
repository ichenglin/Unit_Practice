package com.ichenglin;

import com.ichenglin.objects.HanoiDisk;
import com.ichenglin.objects.HanoiTower;
import com.ichenglin.utility.Graphics;

import java.util.Scanner;

public class Template {

    private static int tower_selected = -1 /* <-- any default value */;
    private static int tower_pointer  = 1  /* <-- any default value */;

    /**
     * Check whether a disk could be legally moved from a tower to another (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     * @param disk_from The original (source) tower ID of the disk
     * @param disk_to The new (destination) tower ID of the disk
     * @return Whether a disk could be legally moved from a tower to another
     */
    public static boolean disk_movable(HanoiTower board, int disk_from, int disk_to) {
        // student code here
        return false;
    }

    /**
     * Legally moves a disk between two towers (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     * @param tower_a The first tower, could be either the source or destination of the disk
     * @param tower_b The second tower, could be either the source or destination of the disk
     */
    public static void disk_movement_legal(HanoiTower board, int tower_a, int tower_b) {
        // student code here
        return;
    }

    /**
     * Solves the Tower of Hanoi, one disk movement per execution (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     */
    public static void hanoi_tower_solution(HanoiTower board) {
        // student code here
        return;
    }

    /**
     * Controls the Tower of Hanoi with Scanner class, one disk movement per execution (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     */
    public static void hanoi_tower_controller(HanoiTower board) {
        // student code here
        return;
    }


}
