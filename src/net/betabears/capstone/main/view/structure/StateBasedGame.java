package net.betabears.capstone.main.view.structure;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.view.structure.state.GameState;
import net.betabears.capstone.main.view.structure.state.GameStateID;
import net.betabears.capstone.main.view.structure.state.State;
import net.betabears.capstone.main.view.structure.state.SuspendableGameState;

import java.util.*;

public class StateBasedGame {
    private HashMap<GameStateID, GameState> gameStates = new HashMap<>();
    private Stack<GameState> lastStates;
    private GameState currentState;
    private Map<String, Object> data;
    private GUIScreen screen;

    public StateBasedGame(GUIScreen screen) {
        this.screen = screen;
        lastStates = new Stack<>();
    }

    public void addGameState(GameState state) {
        if (!state.getClass().isAnnotationPresent(State.class)) {
            throw new RuntimeException("State " + state.getClass().getName() + " does not have a valid @State Annotation.");
        }
        gameStates.put(state.getClass().getAnnotation(State.class).value(), state);
    }

    /**
     * Enter the state registered with given id. This method destroys the current state. If you want to resume the current state later, use {@link #openState(net.betabears.capstone.main.view.structure.state.GameStateID) openState} instead.
     * @param id ID to identify the next State
     */
    public void enterState(GameStateID id) {
        if (currentState != null) {
            currentState.destroy();
            lastStates.clear();
        } else {
            data = new HashMap<>();
        }
        gotoState(id);
    }

    /**
     * Enter the state registered with given id. This method suspends the current state so it can be resumed later.
     * @param id ID to identify the next State
     */
    public void openState(GameStateID id) {
        if (currentState instanceof SuspendableGameState) {
            SuspendableGameState suspendableGameState = (SuspendableGameState) currentState;
            suspendableGameState.suspend();
        } else {
            currentState.destroy();
        }
        lastStates.push(currentState);
        gotoState(id);
    }

    public void resumeLastState() {
        currentState.destroy();
        currentState = lastStates.pop();
        if (currentState instanceof SuspendableGameState) {
            ((SuspendableGameState)currentState).resume();
        } else {
            currentState.init(data, screen, this);
        }
    }

    private void gotoState(GameStateID id) {
        currentState = gameStates.get(id);
        currentState.init(data, screen, this);
    }
}
