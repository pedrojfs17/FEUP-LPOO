package com.g51.pokemon.model.pokemon;

import java.io.Serializable;

public class Attack implements Serializable {
    private String name;
    private int damage;
    private double accuracy;
    private Types type;

    public Attack(String name,int damage, double accuracy, Types type){
        this.name=name;
        this.damage=damage;
        this.accuracy=accuracy;
        this.type=type;
    }

    public String getName() {
        return name;
    }

    public Types getType() {
        return type;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public int getDamage() {
        return damage;
    }

    public void useAttack(int damage, Pokemon target){
        target.applyDamage(damage);
    }

    @Override
    public String toString() {
        return name +"  Damage: " + damage +"  Type=" + type;
    }
}
