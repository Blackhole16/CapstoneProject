package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;
import net.betabears.capstone.main.view.structure.state.SuspendableGameState;

import java.util.Map;

@State(GameStateID.MapChooser)
public class MapChooser implements SuspendableGameState {
    private GUIScreen screen;
    private StateBasedGame sbg;
    private Map<String, Object> data;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.data = data;
        this.screen = screen;
        this.sbg = sbg;
        screen.showWindow(new MapChooserWindow(this), GUIScreen.Position.CENTER);
    }

    @Override
    public void suspend() {}

    @Override
    public void resume() {}

    @Override
    public void destroy() {
        screen.getActiveWindow().close();
    }

    public void startGame(String map) {
        data.put("MapChooser.mapname", map);
        sbg.enterState(GameStateID.StoryIntro);
    }

    public void back() {
        sbg.enterState(GameStateID.MainMenu);
    }
}
