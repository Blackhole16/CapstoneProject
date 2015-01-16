package net.betabears.capstone.main.model.entity;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import net.betabears.capstone.main.model.Model;

public class Entity extends Model {
    protected int x;
    protected int y;

    public Entity(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public void move(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void draw(Screen screen, Terminal.Color background, int fromX, int fromY) {
        screen.putString(x - fromX, y - fromY, String.valueOf(icon), foreground, background, ScreenCharacterStyle.Bold);
    }
}
