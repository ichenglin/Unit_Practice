package com.ichenglin.objects;

public class HanoiDisk {

    private final byte disk_id;

    public HanoiDisk(byte disk_id) {
        this.disk_id = disk_id;
    }

    public byte get_id() {
        return this.disk_id;
    }

    @Override
    public String toString() {
        return String.valueOf(this.disk_id);
    }

}
