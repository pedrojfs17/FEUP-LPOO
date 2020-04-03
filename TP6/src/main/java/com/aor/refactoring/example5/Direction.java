package com.aor.refactoring.example5;

public interface Direction {
    void rotateLeft();
    void rotateRight();
    void moveForward();
    void setTurtle(Turtle turtle);
    char getDirection();
}
