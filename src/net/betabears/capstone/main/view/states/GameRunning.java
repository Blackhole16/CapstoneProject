package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import net.betabears.capstone.main.controller.Direction;
import net.betabears.capstone.main.controller.Entities;
import net.betabears.capstone.main.event.GameListener;
import net.betabears.capstone.main.event.KeyEvent;
import net.betabears.capstone.main.model.entity.unit.Player;
import net.betabears.capstone.main.model.map.Coordinate;
import net.betabears.capstone.main.model.map.MapFactory;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.KeyHandlingGameState;
import net.betabears.capstone.main.view.structure.state.State;

import java.util.Map;

@State(GameStateID.GameRunning)
public class GameRunning extends KeyHandlingGameState implements GameListener {
    private GUIScreen screen;
    private StateBasedGame sbg;
    private net.betabears.capstone.main.controller.Map map;
    private Entities entities;

    private int currentX;
    private int currentY;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        super.init(data, screen, sbg);
        this.screen = screen;
        this.sbg = sbg;

        String mapName = (String) data.get("MapChooser.mapname");
        boolean loaded = mapName == null;
        if (!loaded) {
            entities = MapFactory.getInstance().getEntities(mapName);
        } else {
            entities = (Entities) data.get("LoadMenu.entities");
        }
        entities.addGameListener(this);
        data.put("GameRunning.entities", entities);
        this.map = entities.getMap();
        Coordinate entrance = map.getEntranceCoordinate();

        screen.getScreen().clear();
        if (loaded) {
            Player p = entities.getPlayer();
            centerOn(p.getX(), p.getY());
        } else {
            centerOn(entrance.getX(), entrance.getY());
        }
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void keyPressed(KeyEvent event) {
        Player p = entities.getPlayer();

        switch (event.getKeyKind()) {
            case ArrowUp:
            case ArrowRight:
            case ArrowDown:
            case ArrowLeft:
                entities.movePlayer(Direction.getDirectionByKeyKind(event.getKeyKind()));
                break;
            case NormalKey:
                switch (event.getKeyChar()) {
                    case ' ':
                        centerOn(p.getX(), p.getY());
                        return;
                }
                break;
            case Escape:
                sbg.openState(GameStateID.GameMenu);
                break;
        }

        entities.calcEnemies();
        render(p);
    }

    private void centerOn(int x, int y) {
        int cols = screen.getScreen().getTerminalSize().getColumns();
        int rows = screen.getScreen().getTerminalSize().getRows();

        currentX = x - cols/2;
        currentY = y - rows /2;

        draw(currentX, currentY, currentX + cols, currentY + rows, cols, rows);
    }

    public void render(Player p) {
        int cols = screen.getScreen().getTerminalSize().getColumns();
        int rows = screen.getScreen().getTerminalSize().getRows();

        if (p.getX() < currentX) {
            currentX -= cols;
        } else if (p.getX() >= currentX+cols) {
            currentX += cols;
        }
        if (p.getY() < currentY) {
            currentY -= rows-1;
        } else if (p.getY() >= currentY+rows-1) {
            currentY += rows-1;
        }

        draw(currentX, currentY, currentX+cols, currentY+rows, cols, rows);
    }

    public void draw(int fromX, int fromY, int toX, int toY, int cols, int rows) {
        map.draw(screen.getScreen(), fromX, fromY, toX, toY-1);
        entities.draw(screen.getScreen(), fromX, fromY, toX, toY-1);
        String life = "Life: " + new String(new char[entities.getPlayer().getLife()]).replaceAll("\0", "♥");
        String keys = "Keys: " + entities.getPlayer().getKeys();
        String status = life + "    " + keys;
        status += new String(new char[cols-status.length()]).replaceAll("\0", " ");
        screen.getScreen().putString(0, rows-1, status, Terminal.Color.BLACK, Terminal.Color.GREEN, ScreenCharacterStyle.Bold);
        screen.getScreen().refresh();
    }

    @Override
    public void gameWon() {
        sbg.enterState(GameStateID.GameWon);
    }

    @Override
    public void gameLost() {
        sbg.enterState(GameStateID.GameLost);
    }
}
