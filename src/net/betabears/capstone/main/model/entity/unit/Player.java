package net.betabears.capstone.main.model.entity.unit;

import com.googlecode.lanterna.terminal.Terminal;

public class Player extends Unit {
    private int keys;

    public Player(int x, int y) {
        super(x, y, 3);
        icon = '♔';

        foreground = Terminal.Color.GREEN;
        background = Terminal.Color.BLACK;
    }

    public void addKey() {
        keys++;
    }

    public void useKey() {
        keys--;
    }

    public int getKeys() {
        return keys;
    }
}
