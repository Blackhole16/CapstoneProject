package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import net.betabears.capstone.main.model.map.MapFactory;

import java.util.Arrays;

public class MapChooserWindow extends Window {
    public MapChooserWindow(MapChooser mapChooser) {
        super("Map Chooser");
        MapFactory m = MapFactory.getInstance();
        Arrays.asList(m.getMapNames()).stream().forEach(s -> addComponent(new Button(s, () -> mapChooser.startGame(s))));
        addComponent(new Button("Back", mapChooser::mainMenu));
    }
}
