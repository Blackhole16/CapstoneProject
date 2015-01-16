package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class HelpWindow extends Window {

    public HelpWindow(Help help) {
        super("You Failed");
        addComponent(new Label("Icons"));
        addComponent(new Label("⋃    Entrance"));
        addComponent(new Label("⋂    Exit"));
        addComponent(new Label("…    Static Enemy"));
        addComponent(new Label("D Dynamic Enemy"));
        addComponent(new Label("♔ You (Player)"));
        addComponent(new Label());
        addComponent(new Label(""));
        addComponent(new Label());
        addComponent(new Label());
        addComponent(new Button("Return to Main Menu", help::mainMenu));
    }
}
