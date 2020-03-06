import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.Random;

public class Follower extends Element {
    public Follower(int x, int y) {
        super(x, y);
    }

    public void draw(TextGraphics graphics) {
        graphics.setForegroundColor(TextColor.Factory.fromString("#FF6600"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(position.getX(), position.getY()), "F");
    }

    public Position move(Position playerPosition) {
        Position p = new Position(position.getX(), position.getY());

        // Same Y
        if (playerPosition.getY() == p.getY()) {
            if (playerPosition.getX() > p.getX())
                p.setX(p.getX() + 1);
            else
                p.setX(p.getX() - 1);
        }

        // Same X
        else if (playerPosition.getX() == p.getX()) {
            if (playerPosition.getY() > p.getY())
                p.setY(p.getY() + 1);
            else
                p.setY(p.getY() - 1);
        }

        // Diagonal
        else if (Math.abs(playerPosition.getY() - p.getY()) > Math.abs(playerPosition.getX() - p.getX())) {
            if (playerPosition.getY() > p.getY())
                p.setY(p.getY() + 1);
            else
                p.setY(p.getY() - 1);
        }
        else {
            if (playerPosition.getX() > p.getX())
                p.setX(p.getX() + 1);
            else
                p.setX(p.getX() - 1);
        }

        return p;
    }
}
