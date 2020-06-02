package com.g51.pokemon.model.battle;

import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import java.util.Random;

public class BattleMechanics {
    public int criticalHit(Pokemon user){
        Random r = new Random();
        double probability = user.getSpeed()/255.0;

        if (r.nextInt(255) < probability*100) return 2;
        else return 1;
    }

    private double effectiveFire(Pokemon target){
        if(target.getType()== Types.FIRE || target.getType()== Types.WATER)return 0.5;
        if(target.getType()== Types.GRASS)return 2;
        return 1;
    }

    private double effectiveWater(Pokemon target){
        if(target.getType()== Types.GRASS || target.getType()== Types.WATER) return 0.5;
        if(target.getType()== Types.FIRE) return 2;
        return 1;
    }

    private double effectiveGrass(Pokemon target){
        if(target.getType()== Types.GRASS || target.getType()== Types.FIRE) return 0.5;
        if(target.getType()== Types.WATER) return 2;
        return 1;
    }

    public double effectiveType(Attack user, Pokemon target){
        if(user.getType()==Types.FIRE) return effectiveFire(target);
        if(user.getType()==Types.WATER) return effectiveWater(target);
        if(user.getType()==Types.GRASS) return effectiveGrass(target);
        return 1;
    }

    public boolean attemptHit(Attack attack){
        Random r = new Random();
        return (attack.getAccuracy() * 100) >= r.nextInt(101);
    }

    public int calculateDamage(Attack attack, Pokemon user, Pokemon target){
        return (int) ((((2 * user.getLevel() / 5 + 2) * attack.getDamage() * (user.getAtk()/target.getDef()) / 50) + 2) * criticalHit(user)*effectiveType(attack, target));
    }

    public boolean catchProbability(Pokemon target){
        Random r = new Random();
        return ((target.getOg_health()*255*4)/(target.getHealth()*8))>=r.nextInt(256);
    }

    public boolean runProbability(Pokemon user, Pokemon target, int tries){
        if(target.getSpeed() > user.getSpeed()){
            int f=(user.getSpeed()*32)/(target.getSpeed()/4%255)+30*tries;
            if(!(f>255)){
                Random r = new Random();
                return r.nextInt(256)<f;
            }
            return true;
        }
        return true;
    }

    public int deliverRewards() {
        Random r = new Random();
        return r.nextInt(500)+100;
    }

    public int payForLose() {
        Random r = new Random();
        return r.nextInt(200)+100;
    }

    public int experienceGained(Pokemon target) {
        return 100 * target.getLevel() / 7;
    }
}
