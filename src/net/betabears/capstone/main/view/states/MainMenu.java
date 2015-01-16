package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;
import net.betabears.capstone.main.view.structure.state.SuspendableGameState;

import java.util.Map;

@State(GameStateID.MainMenu)
public class MainMenu implements SuspendableGameState {
    private GUIScreen screen;
    private StateBasedGame sbg;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.screen = screen;
        this.sbg = sbg;
        screen.showWindow(new MainMenuWindow(this), GUIScreen.Position.CENTER);
    }

    @Override
    public void suspend() {}

    @Override
    public void resume() {}

    @Override
    public void destroy() {
        screen.getActiveWindow().close();
    }

    public void newGame() {
        sbg.enterState(GameStateID.MapChooser);
    }

    public void load() {
        sbg.enterState(GameStateID.LoadGame);
    }

    public void help() {
        sbg.enterState(GameStateID.Help);
    }

    public void exit() {
        System.exit(0);
    }
}
