package net.betabears.capstone.main.event;

import com.googlecode.lanterna.input.Key;
import com.googlecode.lanterna.screen.Screen;

public class KeyThread extends Thread {
    private Screen screen;
    private KeyAdapter adapter;
    private boolean interrupted;

    public KeyThread(Screen screen) {
        this.screen = screen;
        adapter = new KeyAdapter();
    }

    public void addKeyListener(KeyListener l) {
        adapter.addKeyListener(l);
    }

    public void removeKeyListener(KeyListener l) {
        adapter.removeKeyListener(l);
    }

    /**
     * Checks each 10ms if a Key has been pressed. If so, it notifies all Listeners.
     */
    @Override
    public void run() {
        while (!interrupted) {
            Key key = screen.readInput();
            if (key != null) {
                adapter.keyPressed(new KeyEvent(key));
            }
            try {
                sleep(10);
            } catch (InterruptedException e) {
                return;
            }
        }
    }

    /**
     * For whatever reason Java's interrupt didn't set interrupted true. IsInterrupted() didn't return true. To not override the Thread's interrupt-method I named this function like this.
     */
    public void interruptThisFuckingShit() {
        this.interrupted = true;
    }
}
