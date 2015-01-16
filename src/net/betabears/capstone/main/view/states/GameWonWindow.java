package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class GameWonWindow extends Window {

    public GameWonWindow(GameWon gameWon) {
        super("You WON.");
        addComponent(new Label("You have had your chance."));
        addComponent(new Label("And you used it!"));
        addComponent(new Label("After your arrival the morale of your knights got a huge boost."));
        addComponent(new Label("After a long Battle your Kingdom finally won!"));
        addComponent(new Label("Everyone loves you."));
        addComponent(new Label());
        addComponent(new Label());
        addComponent(new Label());
        addComponent(new Button("Return to Main Menu", gameWon::mainMenu));
    }
}
