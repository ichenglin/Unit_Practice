package com.ichenglin;

import com.ichenglin.objects.HanoiDisk;
import com.ichenglin.objects.HanoiTower;
import com.ichenglin.utility.Graphics;

import java.util.Scanner;

public class Solution {

    private static int tower_selected = -1;
    private static int tower_pointer = 1;

    // test place, should be removed in production
    public static void main(String[] args) {
        HanoiTower board = new HanoiTower(new byte[][]{{10, 9, 8, 7, 6, 5, 4, 3, 2, 1}, {}, {}}, 0);
        /*int solution_moves = (int) (Math.pow(2, board.get_disks()) - 1);
        System.out.println(board);
        for (int move_index = 0; move_index < solution_moves; move_index++) {
            hanoi_tower_solution(board);
            System.out.println(board);
        }*/
        System.out.println(board);
        while (true) hanoi_tower_controller(board);
    }

    /**
     * Check whether a disk could be legally moved from a tower to another (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     * @param disk_from The original (source) tower ID of the disk
     * @param disk_to The new (destination) tower ID of the disk
     * @return Whether a disk could be legally moved from a tower to another
     */
    public static boolean disk_movable(HanoiTower board, int disk_from, int disk_to) {
        if (board.get_top(disk_from) == null) return false; // nothing to move
        if (board.get_top(disk_to) != null && board.get_top(disk_from).get_id() > board.get_top(disk_to).get_id()) return false; // invalid move
        return true;
    }

    public static int disk_movement_cost(HanoiTower board, int disk_from, int disk_to) {
        return 0;
    }

    /**
     * Legally moves a disk between two towers (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     * @param tower_a The first tower, could be either the source or destination of the disk
     * @param tower_b The second tower, could be either the source or destination of the disk
     */
    public static void disk_movement_legal(HanoiTower board, int tower_a, int tower_b) {
        HanoiDisk tower_a_disk = board.get_top(tower_a); // top disk, possibly null
        HanoiDisk tower_b_disk = board.get_top(tower_b); // top disk, possibly null
        // the ternary operator here could be replaced with several if-else statements
        int tower_a_weight = tower_a_disk != null ? tower_a_disk.get_id() : Integer.MAX_VALUE; // weight MAX if null
        int tower_b_weight = tower_b_disk != null ? tower_b_disk.get_id() : Integer.MAX_VALUE; // weight MAX if null
        if (tower_a_weight < tower_b_weight) {
            board.move_disk(tower_a, tower_b);
        } else if (tower_a_weight > tower_b_weight) {
            board.move_disk(tower_b, tower_a);
        }
    }

    /**
     * Solves the Tower of Hanoi, one disk movement per execution (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     */
    public static void hanoi_tower_solution(HanoiTower board) {
        // the switch statement here could be replaced with several if-else statements
        switch (board.get_moves() % 3) {
            case 0 -> disk_movement_legal(board, 0, 2);
            case 1 -> disk_movement_legal(board, 0, 1);
            case 2 -> disk_movement_legal(board, 1, 2);
        }
    }

    /**
     * Controls the Tower of Hanoi with Scanner class, one disk movement per execution (Unit 2 & Unit 3)
     * @param board The Hanoi Tower object
     */
    public static void hanoi_tower_controller(HanoiTower board) {
        // scanner used for input
        System.out.print("Type in " + Graphics.RED_LIGHT + "[A]" + Graphics.RESET + " or " + Graphics.RED_LIGHT + "[D]" + Graphics.RESET + " to Switch between Towers,\nand " + Graphics.RED_LIGHT + "[Space]" + Graphics.RESET + " to Select for Disk Movement: ");
        Scanner scanner = new Scanner(System.in);
        String scanner_line = scanner.nextLine();
        char scanner_char = scanner_line.length() == 1 ? scanner_line.toLowerCase().charAt(0) : '0';
        // input instructions
        switch (scanner_char) {
            case 'a':
                if (Solution.tower_pointer <= 0) break; // out of bounds
                Solution.tower_pointer--;
                break;
            case 'd':
                if (Solution.tower_pointer >= 2) break; // out of bounds
                Solution.tower_pointer++;
                break;
            case ' ':
                if (Solution.tower_selected == -1) {
                    // not selected, set selected
                    Solution.tower_selected = Solution.tower_pointer;
                } else {
                    // selected, unset
                    if (disk_movable(board, tower_selected, tower_pointer)) board.move_disk(tower_selected, tower_pointer); // move if movable
                    Solution.tower_selected = -1;
                }
                break;
        }
        // EVERYTHING BELOW WOULD BE OPTIONAL !!!!!
        // EVERYTHING BELOW WOULD BE OPTIONAL !!!!!
        // EVERYTHING BELOW WOULD BE OPTIONAL !!!!!
        int disk_width = board.get_disks() * 2 + 1;
        // animation
        String pointer_string  = "(X)";
        String selected_string = "SELECTED!";
        String pointer_color   = Graphics.GOLD      + Graphics.BOLD;
        String selected_color  = Graphics.RED_LIGHT + Graphics.BOLD;
        System.out.println(board);
        System.out.println(" ".repeat((int) Math.floor(disk_width * (Solution.tower_pointer + 0.5) - pointer_string.length() / 2.0)) + pointer_color + pointer_string + Graphics.RESET);
        if (Solution.tower_selected == -1) return; // no tower selected
        System.out.println(" ".repeat((int) Math.floor(disk_width * (Solution.tower_selected + 0.5) - selected_string.length() / 2.0)) + selected_color + selected_string + Graphics.RESET);
    }


}
