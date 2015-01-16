package net.betabears.capstone.main.view.states;

import com.googlecode.lanterna.gui.Window;
import com.googlecode.lanterna.gui.component.Button;
import com.googlecode.lanterna.gui.component.Label;

public class StoryIntroWindow extends Window {

    public StoryIntroWindow(StoryIntro storyIntro) {
        super("Story");
        addComponent(new Label("Bla ... you're a King ..."));
        addComponent(new Label("Battle between Kingdoms ... bla ..."));
        addComponent(new Label("... You ... prisoned ... ..."));
        addComponent(new Label("Try ... to ... escape"));
        addComponent(new Label());
        addComponent(new Button("Continue", storyIntro::nextState));
    }
}
