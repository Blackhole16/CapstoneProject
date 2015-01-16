package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;
import com.googlecode.lanterna.gui.component.TextBox;
import net.betabears.capstone.main.io.SaveFactory;

import java.util.Arrays;

public class SaveGameWindow extends Window {

    public SaveGameWindow(SaveGame saveGame) {
        super("Game Menu");
        Arrays.stream(SaveFactory.getInstance().getSaveNames()).forEach(a -> addComponent(new Button(a, () -> saveGame.save(a))));
        addComponent(new Label());
        addComponent(new Label("Enter Custom Savename:"));
        TextBox tb = new TextBox("new Savename");
        addComponent(tb);
        addComponent(new Button("Save", () -> saveGame.save(tb.getText())));
        addComponent(new Label());
        addComponent(new Button("Back", saveGame::gameMenu));
    }
}
