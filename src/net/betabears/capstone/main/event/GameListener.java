package net.betabears.capstone.main.event;

public interface GameListener {
    /**
     * This method is called when the player has won the game
     */
    public void gameWon();

    /**
     * This method is called when the player died and has lost
     */
    public void gameLost();
}
