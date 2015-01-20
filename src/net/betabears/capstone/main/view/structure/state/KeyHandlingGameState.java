package net.betabears.capstone.main.view.structure.state;

import com.googlecode.lanterna.gui.GUIScreen;
import net.betabears.capstone.main.event.KeyListener;
import net.betabears.capstone.main.event.KeyThread;
import net.betabears.capstone.main.view.structure.StateBasedGame;

import java.util.Map;

/**
 * The KeyHandlingGameState is designed to be used by non-Menu GameStates. It starts a {@link net.betabears.capstone.main.event.KeyThread}
 * which reads input from {@link com.googlecode.lanterna.screen.Screen Screen} and notifies all registered {@link net.betabears.capstone.main.event.KeyListener}s.
 * }
 */
public abstract class KeyHandlingGameState implements KeyListener, SuspendableGameState {
    private KeyThread keyThread;
    private GUIScreen screen;

    @Override
    public void init(Map<String, Object> data, GUIScreen screen, StateBasedGame sbg) {
        this.screen = screen;
        startKeyListener(screen);
    }

    /**
     * Interrupts {@link net.betabears.capstone.main.event.KeyThread} so it can be restarted on resume.
     * @see net.betabears.capstone.main.view.structure.state.SuspendableGameState#suspend
     */
    @Override
    public void suspend() {
        keyThread.interruptThisFuckingShit();
    }

    /**
     * Restarts the {@link net.betabears.capstone.main.event.KeyThread} by creating and starting a new instance.
     * @see net.betabears.capstone.main.view.structure.state.SuspendableGameState#resume
     */
    @Override
    public void resume() {
        startKeyListener(screen);
    }

    /**
     * Removes this {@link net.betabears.capstone.main.event.KeyListener} from {@link net.betabears.capstone.main.event.KeyThread}
     * and interrupts the {@link net.betabears.capstone.main.event.KeyThread}.
     */
    @Override
    public void destroy() {
        keyThread.removeKeyListener(this);
        keyThread.interruptThisFuckingShit();
    }

    private void startKeyListener(GUIScreen screen) {
        keyThread = new KeyThread(screen.getScreen());
        keyThread.addKeyListener(this);
        keyThread.start();
    }
}
