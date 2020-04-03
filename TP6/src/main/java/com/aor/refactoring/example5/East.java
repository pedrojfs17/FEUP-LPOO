package com.aor.refactoring.example5;

public class East implements Direction {
    Turtle turtle;

    East() {}

    East(Turtle turtle) {this.turtle = turtle;}

    @Override
    public void rotateLeft() {
        turtle.setDirection(new North(turtle));
    }

    @Override
    public void rotateRight() {
        turtle.setDirection(new South(turtle));
    }

    @Override
    public void moveForward() {
        turtle.setColumn(turtle.getColumn() + 1);
    }

    @Override
    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }

    @Override
    public char getDirection() {
        return 'E';
    }
}
