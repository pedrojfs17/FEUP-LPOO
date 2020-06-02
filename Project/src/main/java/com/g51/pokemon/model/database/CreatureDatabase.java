package com.g51.pokemon.model.database;

import com.g51.pokemon.model.pokemon.Attack;
import com.g51.pokemon.model.pokemon.Pokemon;
import com.g51.pokemon.model.pokemon.Types;
import java.io.Serializable;
import java.util.*;

public class CreatureDatabase implements Serializable {
    private List<Pokemon> creatures = new ArrayList<>();
    private AttackDatabase attackDatabase = new AttackDatabase();

    public CreatureDatabase(){
        initializeCreatures();
    }

    private void initializeCreatures(){
        Pokemon p0 = new Pokemon("Lickilicky",110,85,95,50, Types.NORMAL);
        createCreatureSet(p0);
        addCreature(p0);

        Pokemon p1=new Pokemon("Ditto",48,48,48,48, Types.NORMAL);
        createCreatureSet(p1);
        addCreature(p1);

        Pokemon p2=new Pokemon("Snorlax",160,110,65,30, Types.NORMAL);
        createCreatureSet(p2);
        addCreature(p2);

        Pokemon p3=new Pokemon("Rattata",30,56,35,72, Types.NORMAL);
        createCreatureSet(p3);
        addCreature(p3);

        Pokemon p4=new Pokemon("Meowth",40,45,35,90, Types.NORMAL);
        createCreatureSet(p4);
        addCreature(p4);

        Pokemon p5=new Pokemon("Darumaka",70,90,45,50, Types.FIRE);
        createCreatureSet(p5);
        addCreature(p5);

        Pokemon p6=new Pokemon("Cyndaquil",39,52,43,65, Types.FIRE);
        createCreatureSet(p6);
        addCreature(p6);

        Pokemon p7=new Pokemon("Torchic",45,60,40,45, Types.FIRE);
        createCreatureSet(p7);
        addCreature(p7);

        Pokemon p8=new Pokemon("Charmander",39,52,43,65, Types.FIRE);
        createCreatureSet(p8);
        addCreature(p8);

        Pokemon p9=new Pokemon("Chimchar",44,58,44,61, Types.FIRE);
        createCreatureSet(p9);
        addCreature(p9);

        Pokemon p10=new Pokemon("Poliwag",40,50,40,90, Types.WATER);
        createCreatureSet(p10);
        addCreature(p10);

        Pokemon p11=new Pokemon("Magikarp",20,10,55,80, Types.WATER);
        createCreatureSet(p11);
        addCreature(p11);

        Pokemon p12=new Pokemon("Piplup",53,51,53,40, Types.WATER);
        createCreatureSet(p12);
        addCreature(p12);

        Pokemon p13=new Pokemon("Psyduck",50,52,48,55, Types.WATER);
        createCreatureSet(p13);
        addCreature(p13);

        Pokemon p14=new Pokemon("Squirtle",44,48,65,43, Types.WATER);
        createCreatureSet(p14);
        addCreature(p14);

        Pokemon p15=new Pokemon("Tangela",65,55,115,60, Types.GRASS);
        createCreatureSet(p15);
        addCreature(p15);

        Pokemon p16=new Pokemon("Treecko",40,45,35,70, Types.GRASS);
        createCreatureSet(p16);
        addCreature(p16);

        Pokemon p17=new Pokemon("Applin",40,40,80,20, Types.GRASS);
        createCreatureSet(p17);
        addCreature(p17);

        Pokemon p18=new Pokemon("Grookey",50,65,50,65, Types.GRASS);
        createCreatureSet(p18);
        addCreature(p18);

        Pokemon p19=new Pokemon("Bulbasaur",45,49,49,45, Types.GRASS);
        createCreatureSet(p19);
        addCreature(p19);

    }

    private void addCreature(Pokemon pokemon){this.creatures.add(pokemon);}

    public void createCreatureSet(Pokemon pokemon){
        Random random = new Random();

        Map<Integer, Attack> attackMap = new HashMap<>();
        while (attackMap.size()!=4){
            Attack att = attackDatabase.getAttack(random.nextInt(attackDatabase.getAttacks().size()));
            if((att.getType()==Types.NORMAL || att.getType()== pokemon.getType()) && !attackMap.containsValue(att)){
                attackMap.put(attackMap.size(),att);
            }

        }
        pokemon.setAttackSet(attackMap);
    }

    public List<Pokemon> getCreatures() {
        return creatures;
    }

    public Pokemon getCreature(int index) {return creatures.get(index).clone();}

    public int getDatabaseSize(){return creatures.size();}

}
