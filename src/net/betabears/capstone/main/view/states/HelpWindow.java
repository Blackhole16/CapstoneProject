package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class HelpWindow extends Window {

    public HelpWindow(Help help) {
        super("Help");
        addComponent(new Label("Icons"));
        addComponent(new Label("⋃    Entrance"));
        addComponent(new Label("⋂    Exit"));
        addComponent(new Label("…    Static Enemy"));
        addComponent(new Label("D    Dynamic Enemy"));
        addComponent(new Label("♔    You (Player)"));
        addComponent(new Label());
        addComponent(new Label("Controls"));
        addComponent(new Label("↑    Go up"));
        addComponent(new Label("→    Go right"));
        addComponent(new Label("↓    Go down"));
        addComponent(new Label("←    Go left"));
        addComponent(new Label("␣    Center Camera on Player"));
        addComponent(new Label());
        addComponent(new Label());
        addComponent(new Label("Additional Stuff"));
        addComponent(new Label("— Blood"));
        addComponent(new Label("— Camera both centered and not centered on Player"));
        addComponent(new Label("— Window adapts to resize"));
        addComponent(new Label("— Saving and Loading with different Files"));
        addComponent(new Label("— Story"));
        addComponent(new Label("— Multiple Menus"));
        addComponent(new Label("— Automatically reads of all Maps from Folder"));
        addComponent(new Label("— Automatically reads of all Saves from Folder"));
        addComponent(new Label("— self-written State-Based-Engine, even with Annotations"));
        addComponent(new Label("— Modular so new features could be added easily"));
        addComponent(new Label("— Well Structured Tree-like Model"));
        addComponent(new Label("— UML-Diagram of this project"));
        addComponent(new Label("— Built *.jar-File"));
        addComponent(new Label());
        addComponent(new Label());
        addComponent(new Button("Back", help::back));
    }
}
