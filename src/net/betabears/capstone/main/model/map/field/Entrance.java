package net.betabears.capstone.main.model.map.field;


import com.googlecode.lanterna.terminal.Terminal.Color;

public class Entrance extends Door {
    public Entrance() {
        super();
        icon = '⋃';
        doorForeground = Color.GREEN;
        doorBackground = Color.GREEN;
        open();
    }
}
