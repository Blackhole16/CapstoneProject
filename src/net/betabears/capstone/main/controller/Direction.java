package net.betabears.capstone.main.controller;

import com.googlecode.lanterna.input.Key.Kind;

/**
 * This enum is designed to be used by the Controller to get rid of the annoying Key-Kind
 */
public enum Direction {
    Up, Left, Down, Right;

    /**
     * Transforms a {@link com.googlecode.lanterna.input.Key.Kind} to Direction
     * @param kind Kind of Key
     * @return Direction according to kind
     */
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
