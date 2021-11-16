package dsa.models;

import java.util.LinkedList;

public class Pokemon {
    private double level;
    private String name;
    //private String aspect;
    LinkedList<Attack> atackList = new LinkedList<Attack>();
    private double health;
    private String type;

    public Pokemon(){}
    public Pokemon(double level, String name, double health, String type, LinkedList<Attack> atackList){
        this();
        setLevel(level);
        setName(name);
        setHealth(health);
        setAtackList(atackList);
        setType(type);
    }

    public void setLevel(double level){this.level=level;}
    public double getLevel(){return this.level;}

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setType(String type){this.type=type;}
    public String getType(){return this.type;}

    public void setHealth(double health){this.health=health;}
    public double getHealth(){return this.health;}

    public void setAtackList(LinkedList<Attack> atackList){this.atackList=atackList;}
    public LinkedList<Attack> getAtackList(){return this.atackList;}
}
