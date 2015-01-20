package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameState;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;

import java.util.Map;

@State(GameStateID.GameMenu)
public class GameMenu implements GameState {
    private Map<String, Object> data;
    private GUIScreen screen;
    private StateBasedGame sbg;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.data = data;
        this.screen = screen;
        this.sbg = sbg;
        screen.showWindow(new GameMenuWindow(this), GUIScreen.Position.CENTER);
        screen.getScreen().refresh();
    }

    @Override
    public void destroy() {
        screen.getActiveWindow().close();
    }

    /**
     * Resumes last State
     */
    public void back() {
        sbg.resumeLastState();
    }

    /**
     * Opens the Save Menu
     */
    public void save() {
        sbg.openState(GameStateID.SaveGame);
    }

    /**
     * Opens the Save Menu telling it to exit after saving
     */
    public void saveAndExit() {
        data.put("GameMenu.saveAndExit", true);
        sbg.openState(GameStateID.SaveGame);
    }

    /**
     * Opens Help Menu
     */
    public void help() {
        sbg.openState(GameStateID.Help);
    }

    /**
     * Opens Main Menu
     */
    public void mainMenu() {
        sbg.enterState(GameStateID.MainMenu);
    }
}
