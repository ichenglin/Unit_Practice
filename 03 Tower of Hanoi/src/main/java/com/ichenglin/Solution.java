package com.ichenglin;

public class Solution {

    // test place
    public static void main(String[] args) {
        HanoiTower board = new HanoiTower(new byte[][]{{5, 4, 3, 2, 1}, {}, {}}, 0);
        int solution_moves = (int) (Math.pow(2, board.get_disks()) - 1);
        for (int move_index = 0; move_index < solution_moves; move_index++) {
            hanoi_tower_solution(board);
        }
        /*System.out.println(board);
        board.move_disk(0, 1);
        System.out.println(board);
        board.move_disk(0, 2);
        System.out.println(board);
        board.move_disk(1, 0);
        System.out.println(board);*/
    }

    // involved: (Unit 2 Objects) (Unit 3 If-Else Booleans)
    public static boolean disk_movable(HanoiTower board, int disk_from, int disk_to) {
        if (board.get_top(disk_from) == null) return false; // nothing to move
        if (board.get_top(disk_to) != null && board.get_top(disk_from).get_id() > board.get_top(disk_to).get_id()) return false; // invalid move
        return true;
    }

    // involved: (Unit 2 Objects & Math Class) (Unit 3 If-Else Booleans)
    public static int disk_movement_cost(HanoiTower board, int disk_from, int disk_to) {
        return 0;
    }

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

    // involved: (Unit 2 Objects) (Unit 3 If-Else Booleans)
    public static void hanoi_tower_solution(HanoiTower board) {
        // the switch statement here could be replaced with several if-else statements
        switch (board.get_moves() % 3) {
            case 0 -> disk_movement_legal(board, 0, 2);
            case 1 -> disk_movement_legal(board, 0, 1);
            case 2 -> disk_movement_legal(board, 1, 2);
        }
        System.out.println(board);
    }

    // involved: (Unit 2 Objects) (Unit 3 If-Else Booleans)
    public static void hanoi_tower_controller(HanoiTower board) {}


}
