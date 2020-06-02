package com.g51.pokemon.model.database;

import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Types;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class AttackDatabase implements Serializable {
    private List<Attack> attacks = new ArrayList<>();

    public AttackDatabase(){
        initializeAttacks();
    }

    private void initializeAttacks(){
        addAttack(new Attack("Cut",50,0.95, Types.NORMAL));
        addAttack(new Attack("Headbutt",70,1.0, Types.NORMAL));
        addAttack(new Attack("Body Slam",85,1.0, Types.NORMAL));
        addAttack(new Attack("Mega Kick",120,0.75, Types.NORMAL));
        addAttack(new Attack("Heat Wave",95,0.90, Types.FIRE));
        addAttack(new Attack("Magma Storm",100,0.75, Types.FIRE));
        addAttack(new Attack("Pyro Ball",120,0.90, Types.FIRE));
        addAttack(new Attack("Incinerate",60,1.0, Types.FIRE));
        addAttack(new Attack("Water Gun",40,1.0, Types.WATER));
        addAttack(new Attack("Aqua Jet",40,1.0, Types.WATER));
        addAttack(new Attack("Aqua Tail",90,0.9, Types.WATER));
        addAttack(new Attack("Crabhammer",100,0.9, Types.WATER));
        addAttack(new Attack("Vine Whip",45,1.0, Types.GRASS));
        addAttack(new Attack("Leaf Blade",90,1.0, Types.GRASS));
        addAttack(new Attack("Branch Poke",40,1.0, Types.GRASS));
        addAttack(new Attack("Power Whip",120,0.85, Types.GRASS));
    }

    private void addAttack(Attack attack){this.attacks.add(attack);}

    public List<Attack> getAttacks() {
        return attacks;
    }

    public Attack getAttack(int index){
        return attacks.get(index);
    }


}
