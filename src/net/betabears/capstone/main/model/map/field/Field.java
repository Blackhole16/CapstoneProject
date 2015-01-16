package net.betabears.capstone.main.model.map.field;

import com.googlecode.lanterna.terminal.Terminal;
import net.betabears.capstone.main.model.Model;

public class Field extends Model {
    public Field() {
        icon = ' ';
        foreground = Terminal.Color.WHITE;
        background = Terminal.Color.BLACK;
    }
}
