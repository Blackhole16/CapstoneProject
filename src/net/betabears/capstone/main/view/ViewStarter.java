package net.betabears.capstone.main.view;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

public class ViewStarter {
    public static void main(String[] args) {
        // create a new SwingTerminal
        SwingTerminal terminal = TerminalFacade.createSwingTerminal();
        // get and start GUIScreen
        GUIScreen screen = TerminalFacade.createGUIScreen(terminal);
        screen.getScreen().startScreen();
        // init StateBasedGame
        new View(screen);
    }
}
