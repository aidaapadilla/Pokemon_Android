package dsa.models;

public class Map {
    private String name;

    public Map(){}
    public Map(String name){
        this();
        setName(name);
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}
}
