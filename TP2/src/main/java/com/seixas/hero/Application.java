package com.seixas.hero;

import com.seixas.hero.game.Game;

import java.io.IOException;

public class Application {
    public static void main(String[] args) {
        Game game = new Game();

        try {
            game.run();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
