package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.controller.Entities;
import net.betabears.capstone.main.io.SaveFactory;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;
import net.betabears.capstone.main.view.structure.state.SuspendableGameState;

import java.util.Map;

@State(GameStateID.SaveGame)
public class SaveGame implements SuspendableGameState {
    private Map<String, Object> data;
    private GUIScreen screen;
    private StateBasedGame sbg;
    private Entities entities;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.data = data;
        this.screen = screen;
        this.sbg = sbg;
        entities = (Entities) data.get("GameRunning.entities");
        screen.getScreen().refresh();
        screen.showWindow(new SaveGameWindow(this), GUIScreen.Position.CENTER);
    }

    @Override
    public void suspend() {
        destroy();
    }

    @Override
    public void resume() {}

    @Override
    public void destroy() {
        screen.getActiveWindow().close();
    }

    public void save(String saveName) {
        SaveFactory.getInstance().saveEntities(saveName, entities);
        if (data.containsKey("GameMenu.saveAndExit")) {
            System.exit(0);
        }
        sbg.resumeLastState();
    }

    public void gameMenu() {
        sbg.resumeLastState();
    }
}
