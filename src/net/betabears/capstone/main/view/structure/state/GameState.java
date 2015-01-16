package net.betabears.capstone.main.view.structure.state;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.StateBasedGame;

import java.util.Map;

public interface GameState {
    void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg);

    void destroy();
}
