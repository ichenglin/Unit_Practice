package com.ichenglin.objects;

import com.ichenglin.data.HanoiConstants;
import com.ichenglin.utility.Gradient;
import com.ichenglin.utility.Graphics;

public class HanoiTowerUserControl extends HanoiTower {

    private byte selected_tower = 0;
    private boolean selected_state = false;

    public HanoiTowerUserControl(byte[][] disks_placement, int initial_moves) {
        super(disks_placement, initial_moves);
    }

    public void set_selected_tower(int tower_id){
        if(tower_id < 0 || tower_id > 2) throw new IllegalArgumentException("tower id out of bounds");
        this.selected_tower = (byte)tower_id;
    }

    public void set_selected_state(boolean selecting) {
        this.selected_state = selecting;
    }

    public void toggle_selected_state(){
        this.selected_state = !this.selected_state;
    }

    public boolean get_selected_state(){
        return this.selected_state;
    }

    public int get_selected_tower(){
        return this.selected_tower;
    }


    @Override
    public void move_disk(int disk_from, int disk_to) {
        super.move_disk(disk_from, disk_to);
        this.selected_tower += disk_to - disk_from;
        if(this.selected_tower > 2) throw new IllegalArgumentException("tower id out of bounds");
        if(this.selected_tower < 0) throw new IllegalArgumentException("tower id out of bounds");
    }

    @Override
    protected String get_disk_block(byte tower_id, int disk_height) {
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
        boolean is_top_selected = false;
        if(this.selected_state){
            is_top_selected = this.tower_disks[selected_tower].get(this.get_height(selected_tower)-1).equals(loop_disk);
        }
        byte[] background = !is_top_selected ? gradient_generator.get_rgb((float) (disk_id - 1) / (disks_amount - 1)) : HanoiConstants.HANOI_DISK_BLOCK_SELECTED_BACKGROUND;
        byte disk_luminance_inverted = (byte) Math.abs(255 * (1 - Graphics.relative_luminance(background[0], background[1], background[2])));
        byte[] foreground = !is_top_selected ? new byte[]{(byte) disk_luminance_inverted, (byte) disk_luminance_inverted, (byte) disk_luminance_inverted} : HanoiConstants.HANOI_DISK_BLOCK_SELECTED_FOREGROUND;
        String disk_label =
                new Graphics().foreground(foreground[0], foreground[1], foreground[2]).background(background[0], background[1], background[2]) +
                        "[" + "-".repeat(disk_id - 1) + disk_id + "-".repeat(disk_id - String.valueOf(disk_id).length()) + "]" +
                        new Graphics().reset();
        return " ".repeat(disk_margin) + disk_label + " ".repeat(disk_margin);
    }


    @Override
    public String toString() {
        String superString = super.toString();
        StringBuilder spacing = new StringBuilder("");
        int disks_amount = this.get_disks();
        int disks_width = (disks_amount * 2) + 1;
        String pointer = "^^^";
        String selected = "SELECTED";
        String selected_pointer_color = Graphics.RED_LIGHT + Graphics.BOLD;
        String selected_string_color = Graphics.YELLOW + Graphics.BOLD;
        String selected_string_with_spacing = " ".repeat((int) Math.floor(disks_width * (this.selected_tower + 0.5) - pointer.length() / 2.0)) + selected_pointer_color + pointer + Graphics.RESET;
        if(this.selected_state){
            return superString + selected_string_with_spacing + "\n" + " ".repeat((int) Math.floor(disks_width * (this.selected_tower+ 0.5) - selected.length() / 2.0)) + selected_string_color + selected + Graphics.RESET;
        }
        return superString + selected_string_with_spacing;
    }
}

