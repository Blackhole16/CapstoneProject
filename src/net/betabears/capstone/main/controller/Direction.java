package net.betabears.capstone.main.controller;

import com.googlecode.lanterna.input.Key.Kind;

public enum Direction {
    Up, Left, Down, Right;

    public static Direction getDirectionByKeyKind(Kind kind) {
        switch (kind) {
            case ArrowUp:
                return Up;
            case ArrowRight:
                return Right;
            case ArrowDown:
                return Down;
            case ArrowLeft:
                return Left;
            default:
                return null;
        }
    }
}
