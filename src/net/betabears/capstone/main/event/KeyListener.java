package net.betabears.capstone.main.event;

public interface KeyListener {
    /**
     * Called whenever a Key has been pressed, this method should react to the Input
     * @param event {@link net.betabears.capstone.main.event.KeyEvent} containing Information about the pressed Key
     */
    public void keyPressed(KeyEvent event);
}
