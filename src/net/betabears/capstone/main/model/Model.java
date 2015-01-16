package net.betabears.capstone.main.model;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.Terminal.Color;

import java.io.Serializable;

public class Model implements Serializable {
    protected char icon;
    protected Color foreground;
    protected Color background;

    public void draw(Screen screen, int x, int y) {
        if (foreground == null) {
            foreground = Color.DEFAULT;
        }
        if (background == null) {
            background = Color.WHITE;
        }

        screen.putString(x, y, String.valueOf(icon), foreground, background, ScreenCharacterStyle.Bold);
    }

    public Color getBackground() {
        return background;
    }

    public void setBackground(Color background) {
        this.background = background;
    }
}
