package net.betabears.capstone.main.controller;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import net.betabears.capstone.main.model.map.Coordinate;
import net.betabears.capstone.main.model.map.field.Entrance;
import net.betabears.capstone.main.model.map.field.Field;
import net.betabears.capstone.main.model.map.field.FieldFactory;

import java.io.Serializable;
import java.util.Properties;

/**
 * This class is a representation of the Game's Map including functions to draw it.
 */
public class Map implements Serializable {
    private Field[][] fields;
    private int width;
    private int height;

    /**
     * Creates a Field-Array from given Properties
     * @param properties Properties to read from
     */
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

    /**
     *
     * @param x X-Coordinate of Field
     * @param y Y-Coordinate of Field
     * @return Field specified by x and y
     */
    public Field getField(int x, int y) {
        return fields[Math.min(Math.max(x, 0), width)][Math.min(Math.max(y, 0), height)];
    }

    /**
     *
     * @return Width of Map
     */
    public int getWidth() {
        return width;
    }

    /**
     *
     * @return Height of Map
     */
    public int getHeight() {
        return height;
    }

    /**
     * Draws the specified area to the screen
     * @param screen Screen to draw on
     * @param fromX X-Start
     * @param fromY Y-Start
     * @param toX X-End
     * @param toY Y-End
     */
    public void draw(Screen screen, int fromX, int fromY, int toX, int toY) {
        int startX = Math.max(fromX, 0);
        int endX = Math.min(toX, width);
        int startY = Math.max(fromY, 0);
        int endY = Math.min(toY, height);
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

    /**
     *
     * @return Coordinate of Entrance
     */
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
}
