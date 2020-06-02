package com.g51.pokemon.view.gui;

import com.g51.pokemon.model.battle.Battle;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import com.g51.pokemon.model.position.Position;
import com.googlecode.lanterna.*;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.input.KeyStroke;
import com.googlecode.lanterna.input.KeyType;
import com.googlecode.lanterna.screen.TerminalScreen;

public class BattleGui {
    private TerminalScreen screen;
    private Battle battle;

    public void draw(TerminalScreen screen,Battle battle) {
        this.screen=screen;
        this.battle=battle;
        drawBackground();
        drawAttacks();
        drawBattleCreatures();
        drawLog();
    }

    public Gui.KEYS getKeyPressed(KeyStroke input) {
        if(input.getKeyType()==KeyType.Character && input.getCharacter()=='1') return Gui.KEYS.FIRST;
        else if(input.getKeyType()==KeyType.Character && input.getCharacter()=='2') return Gui.KEYS.SECOND;
        else if(input.getKeyType()==KeyType.Character && input.getCharacter()=='3') return Gui.KEYS.THIRD;
        else if(input.getKeyType()==KeyType.Character && input.getCharacter()=='4') return Gui.KEYS.FOURTH;
        else if(input.getKeyType()==KeyType.Character && (input.getCharacter()=='c'||input.getCharacter()=='C')) return Gui.KEYS.C;
        else if(input.getKeyType()==KeyType.Character && (input.getCharacter()=='r' ||input.getCharacter()=='R')) return Gui.KEYS.R;
        else if(input.getKeyType()==KeyType.Character && (input.getCharacter()=='s' ||input.getCharacter()=='S')) return Gui.KEYS.S;
        return Gui.KEYS.NOTHING;
    }

    private void drawBackground(){
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#33cc33"));
        graphics.fillRectangle(new TerminalPosition(0, 0), new TerminalSize(100, 40), ' ');
    }

    private void drawAttacks(){
        Pokemon creature = battle.getPlayerCreature();
        TextGraphics graphics = screen.newTextGraphics();

        int spacingX = 10, spacingY = 3, i = 0;

        for (int y = 25; y < 34; ) {
            for (int x = 4; x < 60; ) {
                graphics.setBackgroundColor(TextColor.Factory.fromString("#787878"));
                graphics.fillRectangle(new TerminalPosition(x, y), new TerminalSize(44, 5), ' ');
                graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
                graphics.fillRectangle(new TerminalPosition(x+2, y+1), new TerminalSize(40, 3), ' ');
                graphics.setForegroundColor(TextColor.Factory.fromString("#0E0E0C"));
                graphics.putString(x+3,y+2, (i+1) +" "+creature.getAttack(i).toString());
                x += 36 + spacingX;
                i++;
            }
            y += 5 + spacingY;
        }
    }

    private void drawBattleCreatures(){
        Pokemon playerCreature = battle.getPlayerCreature();
        Pokemon opponentCreature = battle.getCreature();
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#33cc33"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#0E0E0C"));
        drawCreature(playerCreature,new Position(25,18));
        drawCreature(opponentCreature,new Position(75,18));
        drawHealthBars(playerCreature, new Position(10, 15));
        drawHealthBars(opponentCreature, new Position(60, 15));
        graphics.putString(10,20,playerCreature.toString());
        graphics.putString(60,20,opponentCreature.toString());
        graphics.putString(20, 22, "Level: " + playerCreature.getLevel());
        graphics.putString(70, 22, "Level: " + opponentCreature.getLevel());

    }

    private void drawHealthBars(Pokemon creature, Position position) {
        TextGraphics graphics = screen.newTextGraphics();

        int life = creature.getHealth() * 30 / creature.getOg_health();

        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        if (life > 14)
            graphics.setForegroundColor(TextColor.Factory.fromString("#339933"));
        else if (life > 4)
            graphics.setForegroundColor(TextColor.Factory.fromString("#ffcc00"));
        else
            graphics.setForegroundColor(TextColor.Factory.fromString("#cc0000"));

        if (life > 0)
            graphics.drawRectangle(new TerminalPosition(position.getX(), position.getY()), new TerminalSize(life,1), Symbols.BLOCK_SOLID);
        if (life < 30)
            graphics.drawRectangle(new TerminalPosition(position.getX() + life, position.getY()), new TerminalSize(30 - life,1), ' ');

        graphics.setBackgroundColor(TextColor.Factory.fromString("#33cc33"));
        graphics.setForegroundColor(TextColor.Factory.fromString("#000000"));
        graphics.putString(position.getX(), position.getY() + 1, creature.getHealth() + "/" + creature.getOg_health());
    }

    private void drawCreature(Pokemon creature, Position position){
        if(creature.getType() == Types.WATER){drawCharacter(position,"W","#008AFC");}
        if(creature.getType() == Types.GRASS){drawCharacter(position,"G","#008000");}
        if(creature.getType() == Types.FIRE){drawCharacter(position,"F","#EC4C20");}
        if(creature.getType() == Types.NORMAL){drawCharacter(position,"N","#5C450E");}

    }
    private void drawCharacter(Position position, String character, String color) {
        TextGraphics graphics = screen.newTextGraphics();
        graphics.setBackgroundColor(TextColor.Factory.fromString("#33cc33"));
        graphics.setForegroundColor(TextColor.Factory.fromString(color));

        graphics.enableModifiers(SGR.BOLD);
        graphics.putString(position.getX(), position.getY(), character);
    }

    private void drawLog() {
        TextGraphics graphics = screen.newTextGraphics();
        String message = battle.getMessage();
        String turnMessage;

        if (battle.getTurn())
            turnMessage = "Your turn...";
        else
            turnMessage = "Press any key to continue...";

        graphics.setBackgroundColor(TextColor.Factory.fromString("#787878"));
        graphics.fillRectangle(new TerminalPosition(10, 4), new TerminalSize(80, 7), ' ');
        graphics.setBackgroundColor(TextColor.Factory.fromString("#FFFFFF"));
        graphics.fillRectangle(new TerminalPosition(12, 5), new TerminalSize(76, 5), ' ');
        graphics.setForegroundColor(TextColor.Factory.fromString("#0E0E0C"));
        graphics.putString(14,6,message);
        graphics.putString(14, 8, turnMessage);
    }
}
