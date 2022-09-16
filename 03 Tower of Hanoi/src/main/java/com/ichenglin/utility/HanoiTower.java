package com.ichenglin.utility;

import com.ichenglin.data.HanoiConstants;

import java.util.ArrayList;
import java.util.Arrays;

public class HanoiTower {

    private final ArrayList<HanoiDisk>[] tower_disks = new ArrayList[HanoiConstants.HANOI_TOWER_TOWERS];
    private int disk_moves;

    public HanoiTower(byte[][] disks_placement, int initial_moves) {
        // double for-loop looks like dogshit, might need to enhance this constructor later on
        for (int tower_id = 0; tower_id < HanoiConstants.HANOI_TOWER_TOWERS; tower_id++) {
            this.tower_disks[tower_id] = new ArrayList<HanoiDisk>();
            for (int placement_id = 0; placement_id < disks_placement[tower_id].length; placement_id++) {
                byte disk_id = disks_placement[tower_id][placement_id];
                this.tower_disks[tower_id].add(new HanoiDisk(disk_id));
            }
        }
        this.disk_moves = initial_moves;
    }

    public void move_disk(int disk_from, int disk_to) {
        if (!this.valid_tower_id((byte) disk_from)) throw new IllegalArgumentException("tower id out of bounds"); // out of bounds
        if (!this.valid_tower_id((byte) disk_to)) throw new IllegalArgumentException("tower id out of bounds"); // out of bounds
        if (this.get_top(disk_from) == null) throw new RuntimeException("illegal disk movement"); // nothing to move
        if (this.get_top(disk_to) != null && this.get_top(disk_from).get_id() > this.get_top(disk_to).get_id()) throw new RuntimeException("illegal disk movement"); // invalid move

        int disk_from_top_index = this.tower_disks[disk_from].size() - 1;
        HanoiDisk transfer_disk = this.tower_disks[disk_from].remove(disk_from_top_index);
        this.tower_disks[disk_to].add(transfer_disk);
        this.disk_moves++;
    }

    public int get_height(int tower_id) {
        if (!this.valid_tower_id((byte) tower_id)) throw new IllegalArgumentException("tower id out of bounds"); // out of bounds

        return this.tower_disks[tower_id].size();
    }

    public HanoiDisk get_top(int tower_id) {
        if (!this.valid_tower_id((byte) tower_id)) throw new IllegalArgumentException("tower id out of bounds"); // out of bounds
        if (this.get_height(tower_id) <= 0) return null; // nothing in tower

        int top_disk_index = this.tower_disks[tower_id].size() - 1;
        return this.tower_disks[tower_id].get(top_disk_index);
    }

    public int get_disks() {
        int disks_amount = 0;
        for (int tower_id = 0; tower_id < HanoiConstants.HANOI_TOWER_TOWERS; tower_id++) {
            disks_amount += this.tower_disks[tower_id].size();
        }
        return disks_amount;
    }

    public int get_moves() {
        return this.disk_moves;
    }

    private boolean valid_tower_id(byte tower_id) {
        return 0 <= tower_id && tower_id < this.tower_disks.length;
    }

    @Override
    public String toString() {
        StringBuilder print_result = new StringBuilder();
        for (int tower_id = 0; tower_id < this.tower_disks.length; tower_id++) {
            print_result.append("(" + Arrays.toString(this.tower_disks[tower_id].toArray()) + ") ");
        }
        return "HanoiTower " + print_result;
    }

}

