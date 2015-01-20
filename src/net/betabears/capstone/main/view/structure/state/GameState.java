package net.betabears.capstone.main.view.structure.state;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.StateBasedGame;

import java.util.Map;

/**
 * This Interface defines the basic structure of GameStates. This GameState is not designed to be suspended, instead it is destroyed and reinitiated.
 * If you want your GameState to be suspended, use {@link net.betabears.capstone.main.view.structure.state.SuspendableGameState} instead.
 */
public interface GameState {

    /**
     * Initializes the GameState. This method will be called by {@link net.betabears.capstone.main.view.structure.StateBasedGame} each time the State is entered to.
     * @param data A Map containing information from other GameStates. This Object is used to transfer data between GameStates
     * @param screen The GUIScreen which is drawn on
     * @param sbg The StateBasedGame which is used to switch between States
     */
    void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg);

    /**
     * Finalizes the GameState. This method is called by {@link net.betabears.capstone.main.view.structure.StateBasedGame} when this GameState should finish before a new State is started.
     */
    void destroy();
}
