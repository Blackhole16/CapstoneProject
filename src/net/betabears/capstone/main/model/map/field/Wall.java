package net.betabears.capstone.main.model.map.field;

import com.googlecode.lanterna.terminal.Terminal;

public class Wall extends Field {
    public Wall() {
        icon = ' ';
        foreground = Terminal.Color.WHITE;
        background = Terminal.Color.WHITE;
    }
}
