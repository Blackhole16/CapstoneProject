package net.betabears.capstone.main.controller;

import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.terminal.Terminal;
import net.betabears.capstone.main.event.GameAdapter;
import net.betabears.capstone.main.model.Model;
import net.betabears.capstone.main.model.entity.Entity;
import net.betabears.capstone.main.model.entity.EntityFactory;
import net.betabears.capstone.main.model.entity.Key;
import net.betabears.capstone.main.model.entity.unit.DynamicEnemy;
import net.betabears.capstone.main.model.entity.unit.Player;
import net.betabears.capstone.main.model.entity.unit.StaticEnemy;
import net.betabears.capstone.main.model.map.Coordinate;
import net.betabears.capstone.main.model.map.field.Door;
import net.betabears.capstone.main.model.map.field.Exit;
import net.betabears.capstone.main.model.map.field.Field;
import net.betabears.capstone.main.model.map.field.Wall;

import javax.swing.*;
import java.io.Serializable;
import java.util.*;

public class Entities extends GameAdapter implements Serializable {
    private Map map;

    private Player player;
    private List<DynamicEnemy> dynamicEnemies;
    private List<StaticEnemy> staticEnemies;
    private HashMap<Coordinate, Key> keys;
    private Random rand;
    private Model hitBy;

    public Entities(Properties properties, Map map) {
        this.map = map;
        rand = new Random();

        EntityFactory entityFactory = new EntityFactory();
        Set<String> keys = properties.stringPropertyNames();
        dynamicEnemies = new ArrayList<>();
        staticEnemies = new ArrayList<>();
        this.keys = new HashMap<>();

        int[] i = new int[1];
        String[][] s = new String[1][];
        keys.stream().filter(x -> !x.equals("Height") && !x.equals("Width") && ((i[0] = Integer.parseInt(properties.getProperty(x))) > JFrame.DISPOSE_ON_CLOSE || i[0] == WindowConstants.HIDE_ON_CLOSE)).forEach(x -> addModel(entityFactory.getEntity(properties.getProperty(x), Integer.parseInt((s[0] = x.split(","))[0]), Integer.parseInt(s[0][1]))));
    }

    private void addModel(Entity e) {
        if (e instanceof Player) {
            player = (Player)e;
        } else if (e instanceof DynamicEnemy) {
            dynamicEnemies.add((DynamicEnemy)e);
        } else if (e instanceof StaticEnemy) {
            staticEnemies.add((StaticEnemy)e);
        } else if(e instanceof Key) {
            keys.put(new Coordinate(e.getX(), e.getY()), (Key)e);
        }
    }

    public void movePlayer(Direction d) {
        move(player, d);
    }

    public void calcEnemies() {
        dynamicEnemies.stream().filter(a -> !a.equals(hitBy)).forEach(a -> move(a, Direction.values()[rand.nextInt(4)]));
        hitBy = null;
    }

    public void move(Entity e, Direction d) {
        int x = d == Direction.Left ? e.getX()-1 : d == Direction.Right ? e.getX()+1 : e.getX();
        int y = d == Direction.Up ? e.getY()-1 : d == Direction.Down ? e.getY()+1 : e.getY();
        if (x < 0 || x >= map.getWidth() || y < 0 || y >= map.getHeight()) {
            return;
        }

        Model collision = isEmpty(x, y);
        if (collision == null) {
            e.move(x, y);
            return;
        }

        // Collision
        if (e instanceof Player) {
            Player p = (Player) e;
            if (collision instanceof Key) {
                p.move(x, y);
                p.addKey();
                keys.remove(new Coordinate(x, y));
            } else if (collision instanceof DynamicEnemy || collision instanceof StaticEnemy) {
                damagePlayer();
                hitBy = collision;
            } else if (collision instanceof Exit) {
                Exit exit = (Exit) collision;
                if (exit.isOpen()) {
                    // TODO: ADD CHESS
                    gameWon();
                } else {
                    if (p.getKeys() > 0) {
                        p.useKey();
                        exit.open();
                    }
                }
            }
        } else if (e instanceof DynamicEnemy) {
            if (collision instanceof Player) {
                damagePlayer();
            }
        }
    }

    private void damagePlayer() {
        if (player.damage(1)) {
            gameLost();
            return;
        }
        int x = player.getX();
        int y = player.getY();
        map.getField(x,y).setBackground(Terminal.Color.RED);
        map.getField(x+1,y).setBackground(Terminal.Color.RED);
        map.getField(x-1,y).setBackground(Terminal.Color.RED);
        map.getField(x,y+1).setBackground(Terminal.Color.RED);
        map.getField(x,y-1).setBackground(Terminal.Color.RED);
    }

    private Model isEmpty(int x, int y) {
        List<Entity> entities = new ArrayList<>(dynamicEnemies);
        entities.addAll(staticEnemies);
        entities.addAll(keys.values());
        entities.add(player);
        Field f;
        return (f = map.getField(x, y)) instanceof Wall || f instanceof Door ? f : entities.stream().filter(a -> a.getX() == x && a.getY() == y).findFirst().orElse(null);
    }

    public Player getPlayer() {
        return player;
    }

    public Map getMap() {
        return map;
    }

    public void draw(Screen screen, int fromX, int fromY, int toX, int toY) {
        List<Entity> entities = new ArrayList<>(dynamicEnemies);
        entities.addAll(staticEnemies);
        entities.addAll(keys.values());
        entities.add(player);
        entities.stream().filter(x -> x.getX() >= fromX && x.getX() <= toX && x.getY() >= fromY && x.getY() <= toY).forEach(x -> x.draw(screen, map.getField(x.getX(), x.getY()).getBackground(), fromX, fromY));
    }
}
