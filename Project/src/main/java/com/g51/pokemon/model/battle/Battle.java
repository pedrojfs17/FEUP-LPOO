package com.g51.pokemon.model.battle;

import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.map.Player;
import com.g51.pokemon.model.observer.Observer;
import java.util.ArrayList;
import java.util.List;

public class Battle implements Observer {
    private Player player;
    private Pokemon playerCreature;
    private Pokemon creature;
    private BattleMechanics mechanics;
    private List<Observer> observers;
    private String message;
    private int runTries;

    private Boolean playerTurn;

    @Override
    public void changed() {
        this.notifyObservers();
    }

    public enum STATE{
        IN_PROGRESS,
        WIN,
        LOSE
    }

    public enum PARTY{
        PLAYER,
        OPPONENT
    }

    private STATE state;

    public Battle(Player player, Pokemon creature, BattleMechanics mechanics){
        this.player=player;
        this.playerCreature = player.getCreature(0);
        this.creature=creature;
        this.state=STATE.IN_PROGRESS;
        this.mechanics = mechanics;
        this.observers=new ArrayList<>();
        this.message = "A wild " + this.creature.getName() + " appeared!";
        this.runTries=0;
        playerTurn = this.playerCreature.getSpeed() >= creature.getSpeed();
    }

    public Boolean getTurn() {
        return this.playerTurn;
    }

    public void setState(STATE state) {this.state = state;}

    public void switchPokemon(int pokemonIndex) {
        playerCreature = player.switchPokemon(pokemonIndex);
    }

    public void continueBattle(int input) {
        if (endedBattle()) return;

        if (playerTurn) {
            playerTurn = false;
            nextTurn(PARTY.PLAYER, input);
        }
        else {
            playerTurn = true;
            nextTurn(PARTY.OPPONENT, input);
        }
    }

    private void nextTurn(PARTY player, int input){
        Pokemon creature_user;
        Pokemon creature_target;

        if(player==PARTY.PLAYER){
            creature_user=this.playerCreature;
            creature_target=this.creature;
        }
        else{
            creature_user=this.creature;
            creature_target=this.playerCreature;
        }

        Attack atk= creature_user.getAttack(input);

        if(mechanics.attemptHit(atk)){
            int dmg = mechanics.calculateDamage(atk, creature_user, creature_target);
            atk.useAttack(dmg,creature_target);
            this.message = creature_user.getName() + " used "+atk.getName() + "!";
        }
        else{
            this.message = creature_user.getName() + " missed!";
        }

        if (creature_target.isFainted()) {
            if (creature_target == playerCreature) {
                this.message = playerCreature.getName() + " fainted!";
                Pokemon nextPokemon = this.player.getNextCreature();
                if (nextPokemon == null) {
                    int money = mechanics.payForLose();
                    this.player.subtractMoney(money);
                    this.message += " You lost! You paid " + money + "$ for losing.";
                    this.state = STATE.LOSE;
                } else
                    this.playerCreature = nextPokemon;
            }
            else {
                int money = mechanics.deliverRewards();
                int exp = mechanics.experienceGained(creature);
                this.message = "You won! You received "+money+"$ and gained "+exp+"xp!";
                this.playerCreature.gainExperience(exp);
                this.player.addMoney(money);
                this.state=STATE.WIN;
            }
        }

        this.notifyObservers();

    }
    public boolean canCatch(){
        if(this.player.getTeamSize()>5) {
            this.message = "Your team is full!";
            return false;
        }
        if(this.player.getPokeballs()==0){
            this.message = "You don't have pokeballs!";
            return false;
        }
        return true;
    }

    public boolean catchPokemon(){
        if(!canCatch()) {
            this.notifyObservers();
            return false;
        }
        this.player.subPokeball();
        if(mechanics.catchProbability(this.creature)){
            int money = mechanics.deliverRewards();
            this.message="You caught "+this.creature.getName()+ "! You received "+money + "$!";
            this.player.addCreature(this.creature);
            this.player.addMoney(money);
            this.endBattle();
            this.notifyObservers();
            return true;
        }
        else{
            this.message="Couldn't catch "+this.creature.getName()+ "!";
            this.playerTurn=false;
            this.notifyObservers();
            return false;
        }
    }

    public void runBattle(){
        if(mechanics.runProbability(this.playerCreature,this.creature,this.runTries)){
            this.message="You escaped!";
            this.endBattle();
        }
        else{
            this.message="You couldn't run away!";
            this.runTries++;
            this.playerTurn=false;
        }
        this.notifyObservers();
    }

    public void endBattle() {
        this.state = STATE.WIN;
    }

    public Pokemon getPlayerCreature() {
        return playerCreature;
    }

    public Pokemon getCreature() {
        return creature;
    }

    public STATE getState() {
        return state;
    }

    public String getMessage() {return this.message;}

    public boolean endedBattle(){
        return (this.state==STATE.LOSE|| this.state==STATE.WIN);
    }

    public void addObserver(Observer observer) {
        observers.add(observer);
    }

    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.changed();
        }
    }
}
