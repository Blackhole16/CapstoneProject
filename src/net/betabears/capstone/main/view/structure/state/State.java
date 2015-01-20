package net.betabears.capstone.main.view.structure.state;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * This Annotation is designed to be used to identify GameStates. It is used by {@link net.betabears.capstone.main.view.structure.StateBasedGame}
 * as Key for registering GameStates.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface State {
    GameStateID value();
}
