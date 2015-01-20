package net.betabears.capstone.main.model.entity.unit;

import com.googlecode.lanterna.terminal.Terminal;

public class DynamicEnemy extends Unit {
    public DynamicEnemy(int x, int y) {
        super(x, y, 10);
        icon = 'D';
        foreground = Terminal.Color.RED;
        background = Terminal.Color.BLACK;
    }
}
