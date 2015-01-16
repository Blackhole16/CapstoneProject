package net.betabears.capstone.main.view.structure.state;

public interface SuspendableGameState extends GameState {
    void suspend();

    void resume();
}
