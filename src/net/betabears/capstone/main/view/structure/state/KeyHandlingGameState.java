package net.betabears.capstone.main.view.structure.state;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.event.KeyListener;
import net.betabears.capstone.main.event.KeyThread;
import net.betabears.capstone.main.view.structure.StateBasedGame;

import java.util.Map;

public abstract class KeyHandlingGameState implements KeyListener, SuspendableGameState {
    private KeyThread keyThread;
    private GUIScreen screen;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.screen = screen;
        resume();
    }

    @Override
    public void suspend() {
        keyThread.interruptThisFuckingShit();
    }

    @Override
    public void resume() {
        keyThread = new KeyThread(screen.getScreen());
        keyThread.addKeyListener(this);
        keyThread.start();
    }

    @Override
    public void destroy() {
        keyThread.removeKeyListener(this);
        keyThread.interruptThisFuckingShit();
    }
}
