package dsa.models;

public class Attack {
    private String name;
    private String type;
    private double damage;

    public Attack(){}
    public Attack(String name, String type, double damage){
        this();
        setName(name);
        setType(type);
        setDamage(damage);
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setType(String type){this.type=type;}
    public String getType(){return this.type;}

    public void setDamage(double damage){this.damage=damage;}
    public double getDamage(){return this.damage;}
}
