package net.betabears.capstone.main.event;

import java.util.ArrayList;

public class KeyAdapter {
    private ArrayList<KeyListener> listeners = new ArrayList<>();

    public void addKeyListener(KeyListener l) {
        listeners.add(l);
    }

    public void removeKeyListener(KeyListener l) {
        listeners.remove(l);
    }

    public void keyPressed(KeyEvent e) {
        new ArrayList<>(listeners).stream().forEach(a -> a.keyPressed(e));
    }
}
