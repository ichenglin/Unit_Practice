package com.ichenglin;

public class Solution {

    // test place
    public static void main(String[] args) {
        HanoiTower board = new HanoiTower(new byte[][]{{5, 4, 3, 2, 1}, {}, {}});
        System.out.println(board);
        board.move_disk(0, 1);
        System.out.println(board);
        board.move_disk(0, 2);
        System.out.println(board);
        board.move_disk(1, 0);
        System.out.println(board);
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

    // involved: (Unit 2 Objects) (Unit 3 If-Else Booleans)
    public static void hanoi_tower_solution(HanoiTower board) {}

    // involved: (Unit 2 Objects) (Unit 3 If-Else Booleans)
    public static void hanoi_tower_solution_minimum(HanoiTower board) {}

}
