package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class GameLostWindow extends Window {

    public GameLostWindow(GameLost gameLost) {
        super("You Failed");
        addComponent(new Label("You have had your chance."));
        addComponent(new Label("But you seem to think that no one needs you."));
        addComponent(new Label("It's a pity, but your Kingdom won't even miss you."));
        addComponent(new Label("And do you know why?"));
        addComponent(new Label("It's because everyone is already dead."));
        addComponent(new Label("You just sucked too hard!!!"));
        addComponent(new Label("CU neva again."));
        addComponent(new Label());
        addComponent(new Label());
        addComponent(new Label());
        addComponent(new Button("Return to Main Menu", gameLost::mainMenu));
    }
}
