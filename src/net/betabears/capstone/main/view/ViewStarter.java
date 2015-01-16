package net.betabears.capstone.main.view;

import com.googlecode.lanterna.TerminalFacade;
import com.googlecode.lanterna.gui.GUIScreen;
import com.googlecode.lanterna.terminal.swing.SwingTerminal;

public class ViewStarter {
    public static void main(String[] args) {
        SwingTerminal terminal = TerminalFacade.createSwingTerminal();
        GUIScreen screen = TerminalFacade.createGUIScreen(terminal);
        screen.getScreen().startScreen();
        new View(screen);
    }
}
