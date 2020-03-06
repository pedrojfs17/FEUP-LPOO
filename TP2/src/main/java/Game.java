import com.googlecode.lanterna.TextCharacter;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.Screen;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;

public class Game {
    private Screen screen;
    private Arena arena;

    public Game() {
        arena = new Arena(80, 24, 1);
        try {
            Terminal terminal = new DefaultTerminalFactory().createTerminal();
            this.screen = new TerminalScreen(terminal);

            screen.setCursorPosition(null);   // we don't need a cursor
            screen.startScreen();             // screens must be started
            screen.doResizeIfNecessary();     // resize screen if necessary
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void draw() throws IOException {
        screen.clear();
        arena.draw(screen.newTextGraphics());
        screen.refresh();
    }

    public void run() throws IOException {
        int level = 1;
        while (true) {
            draw();
            KeyStroke key = screen.readInput();
            processKey(key);
            arena.verifyMonsterCollisions();
            if (!arena.checkHeroEnergy()) {
                screen.close();
                System.out.println("You lost!");
                break;
            }
            if (arena.finishedLevel()) {
                level++;
                arena = new Arena(80, 24, level);
            }
            if (key.getKeyType() == KeyType.EOF)
                break;
        }
    }

    private void processKey(KeyStroke key) {
        if (key.getKeyType() == KeyType.Character && key.getCharacter() == 'q') {
            try {
                screen.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        arena.processKey(key);
    }


}
