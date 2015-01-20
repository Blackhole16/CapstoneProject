package net.betabears.capstone.main.view.structure.state;

/**
 * This interface defines all necessary methods to make a GameState suspendable
 * and should be implemented by any GameState needing to be suspended.
 */
public interface SuspendableGameState extends GameState {
    /**
     * This method is called by StateBasedGame notifying the current State to suspend all it's running operations
     * to be resumed later.
     */
    void suspend();

    /**
     * This method is called by StateBasedGame notifying this State to be resumed. It should restart all it's
     * suspended operations.
     */
    void resume();
}
