package net.betabears.capstone.main.model.map.field;

import com.googlecode.lanterna.terminal.Terminal.Color;

public class Door extends Field {
    protected Color doorForeground;
    protected Color doorBackground;
    protected boolean open;

    public Door() {
        close();
    }

    public void open() {
        open = true;
        foreground = doorForeground;
        background = doorBackground;
    }

    public void close() {
        open = false;
        foreground = Color.RED;
        background = Color.RED;
    }

    public boolean isOpen() {
        return open;
    }
}
