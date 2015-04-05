package org.alunev.cubes.model;


public enum Side {
    TOP,
    BOTTOM,
    LEFT,
    RIGHT,

    NONE;

    public static Side[] legalValues() {
        return new Side[] {TOP, BOTTOM, LEFT, RIGHT};
    }
}
