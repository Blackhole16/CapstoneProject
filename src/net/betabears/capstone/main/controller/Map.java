package net.betabears.capstone.main.controller;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;
import net.betabears.capstone.main.model.map.Coordinate;
import net.betabears.capstone.main.model.map.field.Entrance;
import net.betabears.capstone.main.model.map.field.FieldFactory;
import net.betabears.capstone.main.model.map.field.Field;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Properties;

public class Map implements Serializable {
    private Field[][] fields;
    private int width;
    private int height;

    public Map(Properties properties) {
        width = Integer.parseInt(properties.getProperty("Width"));
        height = Integer.parseInt(properties.getProperty("Height"));
        fields = new Field[width][height];
        FieldFactory fieldFactory = new FieldFactory();
        for (int i = 0; i < width; i++) {
            for (int j = 0; j < height; j++) {
                fields[i][j] = fieldFactory.getField(properties.getProperty(i + "," + j, "-1"));
            }
        }
    }

    public Field getField(int x, int y) {
        return fields[Math.min(Math.max(x, 0), width)][Math.min(Math.max(y, 0), height)];
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public void draw(Screen screen, int fromX, int fromY, int toX, int toY) {
        int startX = Math.max(fromX, 0);
        int endX = Math.min(toX, width);
        int startY = Math.max(fromY, 0);
        int endY = Math.min(toX, height);
        for (int i = fromX; i < toX; i++) {
            for (int j = fromY; j < toY; j++) {
                if (startX <= i && i < endX && startY <= j && j < endY) {
                    fields[i][j].draw(screen, i - fromX, j - fromY);
                } else {
                    screen.putString(i - fromX, j - fromY, " ", Terminal.Color.BLACK, Terminal.Color.BLACK, ScreenCharacterStyle.Bold);
                }
            }
        }
    }

    public Coordinate getEntranceCoordinate() {
        for (int i = 0; i < fields.length; i++) {
            for (int j = 0; j < fields[0].length; j++) {
                if (fields[i][j] instanceof Entrance) {
                    return new Coordinate(i, j);
                }
            }
        }
        throw new Error("No Entrance");
    }

    public void draw(Screen screen, int x, int y) {
        fields[x][y].draw(screen, x, y);
    }
}
