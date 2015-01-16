package net.betabears.capstone.main.view;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.states.*;
import net.betabears.capstone.main.view.structure.StateBasedGame;
import net.betabears.capstone.main.view.structure.state.GameStateID;

public class View extends StateBasedGame {
    public View(GUIScreen screen) {
        super(screen);
        addGameState(new MainMenu());
        addGameState(new MapChooser());
        addGameState(new StoryIntro());
        addGameState(new GameRunning());
        addGameState(new GameMenu());
        addGameState(new SaveGame());
        addGameState(new LoadGame());
        addGameState(new GameWon());
        addGameState(new GameLost());
        addGameState(new Help());
        enterState(GameStateID.MainMenu);
    }
}
