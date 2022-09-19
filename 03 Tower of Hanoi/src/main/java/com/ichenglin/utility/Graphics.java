package com.ichenglin.utility;

public class Graphics {

    private String[] graphics_style;

    public static final String RED_DARK     = new Graphics().foreground((byte) 170, (byte) 0  , (byte) 0  ).toString();
    public static final String RED_LIGHT    = new Graphics().foreground((byte) 255, (byte) 85 , (byte) 85 ).toString();
    public static final String GOLD         = new Graphics().foreground((byte) 221, (byte) 214, (byte) 5  ).toString();
    public static final String YELLOW       = new Graphics().foreground((byte) 255, (byte) 255, (byte) 85 ).toString();
    public static final String GREEN_DARK   = new Graphics().foreground((byte) 0  , (byte) 170, (byte) 0  ).toString();
    public static final String GREEN_LIGHT  = new Graphics().foreground((byte) 85 , (byte) 255, (byte) 85 ).toString();
    public static final String AQUA_DARK    = new Graphics().foreground((byte) 0  , (byte) 170, (byte) 170).toString();
    public static final String AQUA_LIGHT   = new Graphics().foreground((byte) 85 , (byte) 255, (byte) 255).toString();
    public static final String BLUE_DARK    = new Graphics().foreground((byte) 0  , (byte) 0  , (byte) 170).toString();
    public static final String BLUE_LIGHT   = new Graphics().foreground((byte) 85 , (byte) 255, (byte) 255).toString();
    public static final String PURPLE_DARK  = new Graphics().foreground((byte) 170, (byte) 0  , (byte) 170).toString();
    public static final String PURPLE_LIGHT = new Graphics().foreground((byte) 255, (byte) 85 , (byte) 255).toString();
    public static final String WHITE        = new Graphics().foreground((byte) 255, (byte) 255, (byte) 255).toString();
    public static final String GRAY_DARK    = new Graphics().foreground((byte) 85 , (byte) 85 , (byte) 85 ).toString();
    public static final String GRAY_LIGHT   = new Graphics().foreground((byte) 170, (byte) 170, (byte) 170).toString();
    public static final String BLACK        = new Graphics().foreground((byte) 221, (byte) 214, (byte) 5  ).toString();
    public static final String BOLD         = new Graphics().bold().toString();

    public Graphics() {
        this.graphics_style = new String[3];
    }

    private Graphics(String[] graphics_style) {
        this.graphics_style = graphics_style;
    }

    /**
     * Applies foreground text color to the following string
     * @param red The red value of the foreground RGB color
     * @param green The green value of the foreground RGB color
     * @param blue The blue value of the foreground RGB color
     * @return The chainable Graphics object
     */
    public Graphics foreground(byte red, byte green, byte blue) {
        this.graphics_style[0] = "\033[38;2;" + Byte.toUnsignedInt(red) + ";" + Byte.toUnsignedInt(green) + ";" + Byte.toUnsignedInt(blue) + "m";
        return this;
    }

    /**
     * Applies background text color to the following string
     * @param red The red value of the background RGB color
     * @param green The green value of the background RGB color
     * @param blue The blue value of the background RGB color
     * @return The chainable Graphics object
     */
    public Graphics background(byte red, byte green, byte blue) {
        this.graphics_style[1] = "\033[48;2;" + Byte.toUnsignedInt(red) + ";" + Byte.toUnsignedInt(green) + ";" + Byte.toUnsignedInt(blue) + "m";
        return this;
    }

    /**
     * Applies bold styling to the following string
     * @return The chainable Graphics object
     */
    public Graphics bold() {
        this.graphics_style[2] = "\033[1m";
        return this;
    }

    /**
     * Reset the styling of the following string
     * @return The chainable Graphics object
     */
    public Graphics reset() {
        this.graphics_style = new String[this.graphics_style.length];
        return this;
    }

    /**
     * Calculates the relative luminance (brightness) of a certain color
     * @param red The red value of the RGB color
     * @param green The green value of the RGB color
     * @param blue The blue value of the RGB color
     * @return The chainable Graphics object
     */
    public static float relative_luminance(byte red, byte green, byte blue) {
        return (float) ((0.2126 * Byte.toUnsignedInt(red) + 0.7152 * Byte.toUnsignedInt(green) + 0.0722 * Byte.toUnsignedInt(blue)) / 255);
    }

    @Override
    public String toString() {
        StringBuilder style_string = new StringBuilder();
        int styles_appended = 0;
        for (int style_index = 0; style_index < this.graphics_style.length; style_index++) {
            if (this.graphics_style[style_index] == null) continue;
            style_string.append(this.graphics_style[style_index]);
            styles_appended++;
        }
        if (styles_appended <= 0) return "\033[0m"; // reset style
        return style_string.toString(); // applied styles
    }

}
