import com.googlecode.lanterna.SGR;
import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.TextColor;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Arena {
    private int width;
    private int height;

    private int level;
    private int numMonsters;
    private int numFollowers;

    private Hero hero;

    private List<Wall> walls;
    private List<Coin> coins;
    private List<Monster> monsters;
    private List<Follower> followers;

    private static int counter = 0;

    public Arena(int width, int height, int level) {
        this.width = width;
        this.height = height;

        this.level = level;
        this.numMonsters = 4 + level;
        this.numFollowers = 2 + level / 3;

        hero = new Hero(10, 10);

        this.walls = createWalls();
        this.coins = createCoins();
        this.monsters = createMonsters();
        this.followers = createFollowers();
    }

    public void draw(TextGraphics graphics) {
        graphics.setBackgroundColor(TextColor.Factory.fromString("#336699"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(width, height), ' ');
        hero.draw(graphics);
        for (Wall wall : walls)
            wall.draw(graphics);
        for (Coin coin : coins)
            coin.draw(graphics);
        for (Monster monster : monsters)
            monster.draw(graphics);
        for (Follower follower : followers)
            follower.draw(graphics);

        // Level Number
        graphics.setBackgroundColor(TextColor.Factory.fromString("#A0A0A0"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(new TerminalPosition(2, 0), "Level: " + level);
    }

    public void moveHero(Position position) {
        if (canHeroMove(position)) {
            hero.setPosition(position);
            for (Coin coin : coins)
                if (coin.getPosition().equals(position)) {
                    retrieveCoins(position);
                    break;
                }
        }
    }

    private boolean canHeroMove(Position position) {
        if (position.getX() >= 0 && position.getX() < width && position.getY() >= 0 && position.getY() < height) {
            for (Wall wall : walls) {
                if (wall.getPosition().equals(position))
                    return false;
            }
            return true;
        }
        else
            return false;
    }

    public void processKey(KeyStroke key) {
        moveMonsters();
        counter++;
        switch (key.getKeyType()) {
            case ArrowUp:
                moveHero(hero.moveUp());
                break;
            case ArrowDown:
                moveHero(hero.moveDown());
                break;
            case ArrowLeft:
                moveHero(hero.moveLeft());
                break;
            case ArrowRight:
                moveHero(hero.moveRight());
                break;
        }
    }

    private List<Wall> createWalls() {
        List<Wall> walls = new ArrayList<>();

        for (int c = 0; c < width; c++) {
            walls.add(new Wall(c, 0));
            walls.add(new Wall(c, height - 1));
        }

        for (int r = 1; r < height - 1; r++) {
            walls.add(new Wall(0, r));
            walls.add(new Wall(width - 1, r));
        }

        return walls;
    }

    private List<Coin> createCoins() {
        Random random = new Random();
        ArrayList<Coin> coins = new ArrayList<>();
        for (int i = 0; i < 5; i++)
            coins.add(new Coin(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return coins;
    }

    private void retrieveCoins(Position position) {
        for (int i = 0; i < coins.size(); i++) {
            if (coins.get(i).getPosition().equals(position)) {
                coins.remove(i);
                break;
            }
        }
    }

    private List<Monster> createMonsters() {
        Random random = new Random();
        ArrayList<Monster> monsters = new ArrayList<>();
        for (int i = 0; i < numMonsters; i++)
            monsters.add(new Monster(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return monsters;
    }

    private List<Follower> createFollowers() {
        Random random = new Random();
        ArrayList<Follower> followers = new ArrayList<>();
        for (int i = 0; i < numFollowers; i++)
            followers.add(new Follower(random.nextInt(width - 2) + 1, random.nextInt(height - 2) + 1));
        return followers;
    }

    private void moveMonsters() {
        Position p;
        for (Monster monster : monsters) {
            p = monster.move();
            if (canHeroMove(p))
                monster.setPosition(p);
        }
        if (counter % 3 != 0) {
            for (Follower follower : followers) {
                p = follower.move(hero.getPosition());
                if (canHeroMove(p))
                    follower.setPosition(p);
            }
        }
    }

    public void verifyMonsterCollisions() {
        for (Monster monster : monsters) {
            if (monster.getPosition().equals(hero.getPosition())) {
                hero.setEnergy(hero.getEnergy() - 1);
                return;
            }
        }
        for (Follower follower : followers) {
            if (follower.getPosition().equals(hero.getPosition())) {
                hero.setEnergy(hero.getEnergy() - 1);
                return;
            }
        }
    }

    public boolean checkHeroEnergy() {
        return hero.getEnergy() > 0;
    }

    public boolean finishedLevel() {
        return (this.coins.size() == 0);
    }
}
