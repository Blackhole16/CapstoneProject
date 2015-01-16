package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;

public class MainMenuWindow extends Window {

    public MainMenuWindow(MainMenu mainMenu) {
        super("Main Menu");
        addComponent(new Button("New Game", mainMenu::newGame));
        addComponent(new Button("Load Game", mainMenu::load));
        addComponent(new Button("Help", mainMenu::help));
        addComponent(new Button("Exit", mainMenu::exit));
    }
}
