package dsa.models;

import java.util.LinkedList;

public class Pokemon {
    private String name;
    private double level;
    //private String aspect;
    LinkedList<Attack> attackList = new LinkedList<Attack>();
    private double health;
    private String type;
    private String image;

    public Pokemon(){}
    public Pokemon(String name,double level, double health, String type, LinkedList<Attack> atackList,String image){
        this();
        setName(name);
        setLevel(level);
        setHealth(health);
        setAtackList(atackList);
        setType(type);
        setImage(image);
    }
    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setLevel(double level){this.level=level;}
    public double getLevel(){return this.level;}

    public void setType(String type){this.type=type;}
    public String getType(){return this.type;}

    public void setHealth(double health){this.health=health;}
    public double getHealth(){return this.health;}

    public void setAtackList(LinkedList<Attack> atackList){this.attackList=atackList;}
    public LinkedList<Attack> getAtackList(){return this.attackList;}

    public Pokemon searchPokemon(int pokemon){ //Per buscar a la base de dades els 3 primers pokemons
        Pokemon firstPokemon = new Pokemon();
        return firstPokemon;
    }
    private void setImage(String image) { this.image=image;}
    public String getImage(){return this.image;}
}
