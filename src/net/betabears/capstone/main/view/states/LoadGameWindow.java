package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import net.betabears.capstone.main.io.SaveFactory;

import java.util.Arrays;

public class LoadGameWindow extends Window {

    public LoadGameWindow(LoadGame loadGame) {
        super("Load Game");
        Arrays.stream(SaveFactory.getInstance().getSaveNames()).forEach(a -> addComponent(new Button(a, () -> loadGame.load(a))));
        addComponent(new Button("Return to Main Menu", loadGame::mainMenu));
    }
}
