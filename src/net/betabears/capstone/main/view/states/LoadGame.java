package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.io.SaveFactory;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameState;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;

import java.util.Map;

@State(GameStateID.LoadGame)
public class LoadGame implements GameState {
    private Map<String, Object> data;
    private GUIScreen screen;
    private StateBasedGame sbg;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.data = data;
        this.screen = screen;
        this.sbg = sbg;
        screen.showWindow(new LoadGameWindow(this), GUIScreen.Position.CENTER);
        screen.getScreen().refresh();
    }

    @Override
    public void destroy() {
        screen.getActiveWindow().close();
    }

    /**
     * Loads File by given Name and starts GameRunning with loaded data
     * @param saveName Name of File to load
     */
    public void load(String saveName) {
        data.clear();
        data.put("LoadMenu.entities", SaveFactory.getInstance().getEntities(saveName));
        sbg.enterState(GameStateID.GameRunning);
    }

    /**
     * Returns to Main Menu
     */
    public void mainMenu() {
        sbg.enterState(GameStateID.MainMenu);
    }
}
