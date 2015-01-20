package net.betabears.capstone.main.model;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal.Color;

import java.io.Serializable;

/**
 * Basic Model every other model extend this class.
 */
public class Model implements Serializable {
    protected char icon;
    protected Color foreground;
    protected Color background;

    /**
     * Draws this Model onto the Screen.
     * @param screen Screen to draw on
     * @param x x-Position
     * @param y y-Position
     */
    public void draw(Screen screen, int x, int y) {
        if (foreground == null) {
            foreground = Color.DEFAULT;
        }
        if (background == null) {
            background = Color.WHITE;
        }

        screen.putString(x, y, String.valueOf(icon), foreground, background, ScreenCharacterStyle.Bold);
    }

    /**
     *
     * @return Background Color of this Model
     */
    public Color getBackground() {
        return background;
    }

    /**
     * Sets the background Color
     * @param background new Background Color
     */
    public void setBackground(Color background) {
        this.background = background;
    }
}
