package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameState;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;

import java.util.Map;

@State(GameStateID.Help)
public class Help implements GameState {
    private GUIScreen screen;
    private StateBasedGame sbg;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.screen = screen;
        this.sbg = sbg;
        screen.showWindow(new HelpWindow(this), GUIScreen.Position.CENTER);
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
}
