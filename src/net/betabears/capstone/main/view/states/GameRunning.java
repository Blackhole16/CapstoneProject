package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.screen.ScreenCharacterStyle;
import com.googlecode.lanterna.terminal.Terminal;
import com.googlecode.lanterna.terminal.TerminalSize;
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
public class GameRunning extends KeyHandlingGameState implements GameListener, Terminal.ResizeListener {
    private GUIScreen screen;
    private StateBasedGame sbg;
    private net.betabears.capstone.main.controller.Map map;
    private Entities entities;

    private boolean centered;
    private int currentX;
    private int currentY;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        super.init(data, screen, sbg);
        this.screen = screen;
        this.sbg = sbg;

        screen.getScreen().getTerminal().addResizeListener(this);

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
        screen.getScreen().getTerminal().removeResizeListener(this);
    }

    @Override
    public void suspend() {
        super.suspend();
        screen.getScreen().getTerminal().removeResizeListener(this);
    }

    @Override
    public void resume() {
        super.resume();
        screen.getScreen().getTerminal().addResizeListener(this);
    }

    @Override
    public void keyPressed(KeyEvent event) {
        Player p = entities.getPlayer();

        switch (event.getKeyKind()) {
            // move Player
            case ArrowUp:
            case ArrowRight:
            case ArrowDown:
            case ArrowLeft:
                entities.movePlayer(Direction.getDirectionByKeyKind(event.getKeyKind()));
                break;
            // for all spaces from Key.getKeyChar ...
            // ∀ ␣ ∈ Key.getKeyChar : centerOn(Player)
            case NormalKey:
                switch (event.getKeyChar()) {
                    case ' ':
                        if (centered ^= true) {
                            centerOn(p.getX(), p.getY());
                        }
                        return;
                }
                break;
            case Escape:
                sbg.openState(GameStateID.GameMenu);
                break;
        }

        // every time the User moves, the enemies do so, too
        entities.calcEnemies();

        if (centered) {
            centerOn(p.getX(), p.getY());
        }
        render(p);
    }

    /**
     * Centers the Camera on x,y
     * @param x X-Coordinate
     * @param y Y-Coordinate
     */
    private void centerOn(int x, int y) {
        int cols = screen.getScreen().getTerminalSize().getColumns();
        int rows = screen.getScreen().getTerminalSize().getRows();

        centerOn(x, y, cols, rows);
    }

    /**
     * Centers the Camera on x,y
     * @param x X-Coordinate
     * @param y Y-Coordinate
     * @param cols Columns on the screen
     * @param rows Rows on the screen
     */
    private void centerOn(int x, int y, int cols, int rows) {
        currentX = x - cols/2;
        currentY = y - rows /2;

        draw(currentX, currentY, currentX + cols, currentY + rows, cols, rows);
    }

    /**
     * Checks if the player is out of the screen and reacts to it
     * @param p Player
     */
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

    /**
     * Draws the specified area to the screen by calling the appropriate method on {@link net.betabears.capstone.main.controller.Entities}
     * and {@link net.betabears.capstone.main.controller.Map Map}
     * @param fromX X-Start
     * @param fromY Y-Start
     * @param toX X-End
     * @param toY Y-End
     * @param cols Columns on the screen
     * @param rows Rows on the screen
     */
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

    @Override
    public void onResized(TerminalSize newSize) {
        // for whatever reason this method is called before the screen gets the new TerminalSize. Calling refresh, it greps and buffers the new Size
        screen.getScreen().refresh();
        centerOn(entities.getPlayer().getX(), entities.getPlayer().getY(), newSize.getColumns(), newSize.getRows());
    }
}
