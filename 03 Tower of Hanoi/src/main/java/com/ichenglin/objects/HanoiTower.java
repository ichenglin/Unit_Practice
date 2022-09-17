package com.ichenglin.objects;

import com.ichenglin.data.HanoiConstants;
import com.ichenglin.utility.Gradient;
import com.ichenglin.utility.Graphics;

import java.util.ArrayList;

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

    private String get_disk_block(byte tower_id, int disk_height) {
        int disks_amount = this.get_disks();
        int disks_width = (disks_amount * 2) + 1;
        if (this.tower_disks[tower_id].size() <= disk_height) {
            // no disk here
            return " ".repeat((int) Math.floor(disks_width / 2.0)) + "|" + " ".repeat((int) Math.floor(disks_width / 2.0));
        }
        HanoiDisk loop_disk = this.tower_disks[tower_id].get(disk_height);
        int disk_id = loop_disk.get_id();
        int disk_margin = disks_amount - loop_disk.get_id();
        Gradient gradient_generator = new Gradient(HanoiConstants.HANOI_DISK_GRADIENT);
        byte[] disk_color = gradient_generator.get_rgb((float) (disk_id - 1) / (disks_amount - 1));
        byte disk_luminance_inverted = (byte) Math.abs(255 * (1 - Graphics.relative_luminance(disk_color[0], disk_color[1], disk_color[2])));
        String disk_label =
                new Graphics().foreground(disk_luminance_inverted, disk_luminance_inverted, disk_luminance_inverted).background(disk_color[0], disk_color[1], disk_color[2]) +
                "[" + "-".repeat(disk_id - 1) + disk_id + "-".repeat(disk_id - String.valueOf(disk_id).length()) + "]" +
                new Graphics().reset();
        return " ".repeat(disk_margin) + disk_label + " ".repeat(disk_margin);
    }

    @Override
    public String toString() {
        StringBuilder tower_image = new StringBuilder();
        int disks_amount = this.get_disks();
        for (int disk_height = disks_amount - 1; disk_height >= 0; disk_height--) {
            for (int tower_id = 0; tower_id < HanoiConstants.HANOI_TOWER_TOWERS; tower_id++) {
                tower_image.append(this.get_disk_block((byte) tower_id, disk_height));
            }
            tower_image.append("\n");
        }
        return "Tower of Hanoi (Moves: " + this.disk_moves + ")\n" + tower_image;
    }

}

