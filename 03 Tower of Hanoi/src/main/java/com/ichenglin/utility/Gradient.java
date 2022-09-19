package com.ichenglin.utility;

/**
 * The linear color gradient generator
 */
public class Gradient {

    byte[][] gradient_anchors;

    public Gradient(byte[][] gradient_anchors) {
        this.gradient_anchors = gradient_anchors;
    }

    /**
     * Calculates the RGB color of a point on the linear gradient
     * @param gradient_progress The point (progress, between 0.0~1.0) on the linear gradient
     * @return The RGB color of a point on the linear gradient
     */
    public byte[] get_rgb(float gradient_progress) {
        if (gradient_progress >= 1) return this.gradient_anchors[this.gradient_anchors.length - 1];
        int gradient_stage = (int) Math.floor(gradient_progress * (this.gradient_anchors.length - 1));
        float gradient_stage_weight = (1.0f / (this.gradient_anchors.length - 1));
        float gradient_stage_progress = (gradient_progress % gradient_stage_weight) / gradient_stage_weight;
        return new byte[]{
                this.get_rgb_component((byte) 0, gradient_stage, gradient_stage_progress),
                this.get_rgb_component((byte) 1, gradient_stage, gradient_stage_progress),
                this.get_rgb_component((byte) 2, gradient_stage, gradient_stage_progress)
        };
    }

    private byte get_rgb_component(byte rgb_index, int gradient_stage, float gradient_stage_progress) {
        int color_origin = Byte.toUnsignedInt(this.gradient_anchors[gradient_stage][rgb_index]);
        int color_destination = Byte.toUnsignedInt(this.gradient_anchors[gradient_stage + 1][rgb_index]);
        return (byte) ((color_destination - color_origin) * gradient_stage_progress + color_origin);
    }

}
