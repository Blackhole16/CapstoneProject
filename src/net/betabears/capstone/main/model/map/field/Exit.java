package net.betabears.capstone.main.model.map.field;

import com.googlecode.lanterna.terminal.Terminal;

public class Exit extends Door {
    public Exit() {
        super();
        icon = '⋂';
        doorForeground = Terminal.Color.RED;
        doorBackground = Terminal.Color.GREEN;
    }
}
