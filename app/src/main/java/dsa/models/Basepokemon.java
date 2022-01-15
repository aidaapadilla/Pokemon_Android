package dsa.models;

public class Basepokemon {
    private String name;
    private String type;
    private Double maxhealth;

    public Basepokemon() {}
    public Basepokemon(String name, String type, Double maxhealth){
        this();
        setName(name);
        setType(type);
        setMaxhealth(maxhealth);
    }

    public void setName(String name){this.name=name;}
    public void setType(String type){this.type=type;}
    public void setMaxhealth(Double health){this.maxhealth =health;}

    public String getName(){return this.name;}
    public String getType(){return this.type;}
    public Double getMaxhealth(){return this.maxhealth;}
}