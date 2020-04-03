package com.aor.refactoring.example5;

public class North implements Direction {
    Turtle turtle;

    North() {}

    North(Turtle turtle) {this.turtle = turtle;}



    @Override
    public void rotateLeft() {
        turtle.setDirection(new West(turtle));
    }

    @Override
    public void rotateRight() {
        turtle.setDirection(new East(turtle));
    }

    @Override
    public void moveForward() {
        turtle.setRow(turtle.getRow() - 1);
    }

    @Override
    public void setTurtle(Turtle turtle) {
        this.turtle = turtle;
    }

    @Override
    public char getDirection() {
        return 'N';
    }
}
