package net.betabears.capstone.main.model.entity.unit;

import com.googlecode.lanterna.terminal.Terminal;

public class StaticEnemy extends Unit {
    public StaticEnemy(int x, int y) {
        super(x, y, 5);
        icon = '…';
        foreground = Terminal.Color.RED;
        background = Terminal.Color.BLACK;
    }
}
