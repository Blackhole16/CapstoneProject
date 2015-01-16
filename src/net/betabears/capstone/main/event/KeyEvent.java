package net.betabears.capstone.main.event;

import com.googlecode.lanterna.input.Key;

public class KeyEvent {
    private Key key;

    public KeyEvent(Key key) {
        this.key = key;
    }

    public Key getKey() {
        return key;
    }

    public Key.Kind getKeyKind() {
        return key.getKind();
    }

    public char getKeyChar() {
        return key.getCharacter();
    }
}
