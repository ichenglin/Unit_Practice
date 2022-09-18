package com.ichenglin;

import com.ichenglin.objects.HanoiDisk;
import com.ichenglin.objects.HanoiTower;
import com.ichenglin.objects.hanoiTower_userControl;

import java.util.Scanner;

public class Solution {

    // test place
    public static void main(String[] args) {
        hanoiTower_userControl board = new hanoiTower_userControl(new byte[][]{{3, 2, 1}, {}, {}}, 0);
        hanoi_tower_controller_loop(board);
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
    }

    public static void hanoi_tower_controller_loop(hanoiTower_userControl board){
        while(true){
            System.out.println(board);
            if(board.get_height(2) == board.get_disks()) {
                board.setSelecting(false);
                System.out.println(board);
                break;
            }
            hanoi_tower_controller(board);
        }
        System.out.println("YOU WINNNNNNNNNN!!!!!!");
    }

    // involved: (Unit 2 Objects) (Unit 3 If-Else Booleans) (IDK what unit but: Scanner)
    public static void hanoi_tower_controller(hanoiTower_userControl board) {
        Scanner scanner = new Scanner(System.in);
        String system_in;
        system_in = scanner.nextLine();
        if(system_in.length()>1){
            system_in.substring(0,1);
        }
        if(system_in.equals(" ")){
            board.setSelecting();
        }
        else {
            int tower_selected = board.getSelectingTower();
            if (!board.getSelectingState()) {
                if (system_in.equals("a")  && tower_selected != 0) {
                    board.select_previous();
                } else if (system_in.equals("d") && tower_selected != 2) {
                    board.select_next();
                }
            } else {
                if (system_in.equals("a")) {
                    if(disk_movable(board, tower_selected, tower_selected - 1) && tower_selected != 0) {
                        board.move_disk(tower_selected, tower_selected - 1);
                    }
                    else if(disk_movable(board, tower_selected, tower_selected - 2) && tower_selected == 2){
                        board.move_disk(tower_selected, 0);
                    }
                } else if (system_in.equals("d") ) {
                    if(disk_movable(board, tower_selected, tower_selected +1) && tower_selected != 2) {
                        board.move_disk(tower_selected, tower_selected + 1);
                    }
                    else if(disk_movable(board, tower_selected, tower_selected + 2) && tower_selected == 0 ){
                        board.move_disk(tower_selected, 2);
                    }
                }
            }
        }
    }
}
