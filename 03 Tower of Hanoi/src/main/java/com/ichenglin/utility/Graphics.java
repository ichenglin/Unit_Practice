package com.ichenglin.utility;

public class Graphics {

    private String[] graphics_style;

    public Graphics() {
        this.graphics_style = new String[2];
    }

    private Graphics(String[] graphics_style) {
        this.graphics_style = graphics_style;
    }

    public Graphics foreground(byte red, byte green, byte blue) {
        this.graphics_style[0] = "38;2;" + Byte.toUnsignedInt(red) + ";" + Byte.toUnsignedInt(green) + ";" + Byte.toUnsignedInt(blue);
        return this;
    }

    public Graphics background(byte red, byte green, byte blue) {
        this.graphics_style[1] = "48;2;" + Byte.toUnsignedInt(red) + ";" + Byte.toUnsignedInt(green) + ";" + Byte.toUnsignedInt(blue);
        return this;
    }

    public Graphics reset() {
        this.graphics_style = new String[2];
        return this;
    }

    public static float relative_luminance(byte red, byte green, byte blue) {
        return (float) ((0.2126 * Byte.toUnsignedInt(red) + 0.7152 * Byte.toUnsignedInt(green) + 0.0722 * Byte.toUnsignedInt(blue)) / 255);
    }

    @Override
    public String toString() {
        StringBuilder style_string = new StringBuilder();
        int styles_appended = 0;
        for (int style_index = 0; style_index < 2; style_index++) {
            if (this.graphics_style[style_index] == null) continue;
            if (styles_appended > 0) style_string.append(";");
            style_string.append(this.graphics_style[style_index]);
            styles_appended++;
        }
        if (styles_appended <= 0) return "\033[0m"; // reset style
        return "\033[" + style_string + "m"; // applied styles
    }

}
