package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameState;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;

import java.util.Map;

@State(GameStateID.MainMenu)
public class MainMenu implements GameState {
    private GUIScreen screen;
    private StateBasedGame sbg;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.screen = screen;
        this.sbg = sbg;
        screen.showWindow(new MainMenuWindow(this), GUIScreen.Position.CENTER);
    }

    @Override
    public void destroy() {
        screen.getActiveWindow().close();
    }

    /**
     * Opens Map Chooser
     */
    public void newGame() {
        sbg.enterState(GameStateID.MapChooser);
    }

    /**
     * Opens Load Menu
     */
    public void load() {
        sbg.enterState(GameStateID.LoadGame);
    }

    /**
     * Opens Help
     */
    public void help() {
        sbg.openState(GameStateID.Help);
    }

    /**
     * Exit Game and Stop VM
     */
    public void exit() {
        System.exit(0);
    }
}
