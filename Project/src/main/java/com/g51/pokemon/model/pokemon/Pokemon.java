package com.g51.pokemon.model.pokemon;

import java.io.Serializable;
import java.util.Map;
import java.util.Objects;

public class Pokemon implements Serializable, Cloneable {
    private String name;
    private int experience;
    private int experienceToNextLevel;
    private int level;
    private int health;
    private int og_health;
    private int atk;
    private int def;
    private int speed;
    private Types type;
    private Map<Integer,Attack> attackSet;

    public Pokemon(String name, int health, int atk, int def, int speed, Types type){
        this.name=name;
        this.level = 1;
        this.experience = 0;
        this.experienceToNextLevel = (int)Math.ceil(4 * Math.pow(level, 3) / 5);
        this.og_health=health;
        this.health=health;
        this.atk=atk;
        this.def=def;
        this.speed=speed;
        this.type=type;
    }

    public Types getType() {
        return type;
    }

    public int getHealth() {
        return health;
    }

    public int getOg_health() {return this.og_health;}

    public String getName() {
        return name;
    }

    public int getAtk() {
        return atk;
    }

    public int getDef() {
        return def;
    }

    public int getSpeed() {
        return speed;
    }

    public Attack getAttack(int index){
        return attackSet.get(index);
    }

    public Map<Integer, Attack> getAttackSet() {
        return attackSet;
    }

    public void resetHealth() {
        this.health = this.og_health;
    }

    public void setAttackSet(Map<Integer, Attack> attackSet) {
        this.attackSet = attackSet;
    }

    public void applyDamage(int amount){
        this.health-=amount;
        if(this.health<0)
            this.health=0;
    }

    public boolean isFainted(){
        return this.health==0;
    }

    @Override
    public String toString() {
        return name +"  Health:" + health +"  type=" + type ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pokemon pokemon = (Pokemon) o;
        return health == pokemon.health &&
                atk == pokemon.atk &&
                def == pokemon.def &&
                speed == pokemon.speed &&
                Objects.equals(name, pokemon.name) &&
                type == pokemon.type &&
                Objects.equals(attackSet, pokemon.attackSet);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, health, atk, def, speed, type, attackSet);
    }

    @Override
    public Pokemon clone() {
        try {
            return (Pokemon) super.clone();
        } catch (CloneNotSupportedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void levelUp() {
        level++;
        experienceToNextLevel = (int)Math.ceil(4 * Math.pow(level, 3) / 5);
        int growHealth = (int)Math.ceil(this.og_health / 50.0);
        this.og_health += growHealth;
        this.health = Math.min(this.health + growHealth * 3, this.og_health);
        this.atk += (int)Math.ceil(this.atk / 50.0);
        this.def += (int)Math.ceil(this.def / 50.0);
        this.speed += (int)Math.ceil(this.speed / 50.0);
    }

    public void gainExperience(int experiencePoints) {
        this.experience += experiencePoints;

        while (experience > experienceToNextLevel) {
            experience -= experienceToNextLevel;
            levelUp();
        }
    }

    public int getLevel() {
        return this.level;
    }

    public void increaseLevel(int level) {
        for (int i = 0; i < level; i++)
            levelUp();
    }
}
