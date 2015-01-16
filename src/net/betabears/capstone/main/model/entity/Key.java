package net.betabears.capstone.main.model.entity;

import com.googlecode.lanterna.terminal.Terminal;

public class Key extends Entity {
    public Key(int x, int y) {
        super(x, y);
        icon = 'K';
        foreground = Terminal.Color.YELLOW;
    }
}
