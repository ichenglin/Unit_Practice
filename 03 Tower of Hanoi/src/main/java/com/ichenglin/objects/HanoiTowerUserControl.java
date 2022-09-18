package com.ichenglin.objects;

import com.ichenglin.data.HanoiConstants;
import com.ichenglin.utility.Gradient;
import com.ichenglin.utility.Graphics;

import static com.ichenglin.data.HanoiConstants.HANOI_DISK_BLOCK_SELECTED_BACKGROUND;
import static com.ichenglin.data.HanoiConstants.HANOI_DISK_BLOCK_SELECTED_FOREGROUND;

public class HanoiTowerUserControl extends HanoiTower {
    private byte selecting_tower = 0;
    private boolean is_selecting = false;
    public HanoiTowerUserControl(byte[][] disks_placement, int initial_moves) {
        super(disks_placement, initial_moves);
    }

    public void select_next(){
        this.selecting_tower++;
        if(this.selecting_tower > 2) throw new IllegalArgumentException("tower id out of bounds");
    }

    public void select_previous(){
        this.selecting_tower--;
        if(this.selecting_tower < 0) throw new IllegalArgumentException("tower id out of bounds");
    }

    public void setSelecting(boolean selecting) {
        this.is_selecting = selecting;
    }

    public void setSelecting(){
        this.is_selecting = !this.is_selecting;
    }

    public boolean getSelectingState(){
        return this.is_selecting;
    }

    public int getSelectingTower(){
        return this.selecting_tower;
    }


    @Override
    public void move_disk(int disk_from, int disk_to) {
        super.move_disk(disk_from, disk_to);
        this.selecting_tower += disk_to - disk_from;
        if(this.selecting_tower > 2) throw new IllegalArgumentException("tower id out of bounds");
        if(this.selecting_tower < 0) throw new IllegalArgumentException("tower id out of bounds");
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
        if(this.is_selecting){
            is_top_selected = this.tower_disks[selecting_tower].get(this.get_height(selecting_tower)-1).equals(loop_disk);
        }
        byte[] background = !is_top_selected ? gradient_generator.get_rgb((float) (disk_id - 1) / (disks_amount - 1)) : HANOI_DISK_BLOCK_SELECTED_BACKGROUND;
        byte[] foreground = !is_top_selected ? new byte[]{(byte) 255, (byte) 255, (byte) 255} : HANOI_DISK_BLOCK_SELECTED_FOREGROUND;
        String disk_label =
                new Graphics().foreground(foreground[0], foreground[1], foreground[2]).background(background[0], background[1], background[2]) +
                        "[" + "-".repeat(disk_id - 1) + disk_id + "-".repeat(disk_id - String.valueOf(disk_id).length()) + "]" +
                        new Graphics().reset();
        return " ".repeat(disk_margin) + disk_label + " ".repeat(disk_margin);
    }


    @Override
    public String toString() {
        String superString = super.toString();
        StringBuilder spacing = new StringBuilder(" ");
        int disks_amount = this.get_disks();
        int disks_width = (disks_amount * 2) + 1;
        for(int tower_index = 0; tower_index < this.selecting_tower; tower_index++){
                spacing.append(" ".repeat((int) Math.floor(disks_width)) + "  " );
        }
        return this.is_selecting ? superString + spacing + "^Selecting" : superString + spacing + "^";
    }
}

