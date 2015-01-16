package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;

public class GameMenuWindow extends Window {

    public GameMenuWindow(GameMenu gameMenu) {
        super("Game Menu");
        addComponent(new Button("Resume", gameMenu::retur));
        addComponent(new Button("Save", gameMenu::save));
        addComponent(new Button("Save and Exit", gameMenu::saveAndExit));
        addComponent(new Button("Help", gameMenu::help));
        addComponent(new Button("Return to Main Menu", gameMenu::mainMenu));
    }
}
