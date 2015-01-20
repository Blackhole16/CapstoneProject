package net.betabears.capstone.main.event;

import java.util.ArrayList;
import java.util.List;

public class GameAdapter {
    private List<GameListener> listeners = new ArrayList<>();

    public void addGameListener(GameListener l) {
        listeners.add(l);
    }

    public void removeGameListener(GameListener l) {
        listeners.remove(l);
    }

    public void gameWon() {
        listeners.stream().forEach(GameListener::gameWon);
    }

    public void gameLost() {
        listeners.stream().forEach(GameListener::gameLost);
    }
}
