package dsa.models;

public class Map {
    private String name;
    private Double level;

    public Map(){}

    public Map(String name, Double level){
        setName(name);
        setLevel(level);

    }
    public void setName(String name){this.name=name;}
    public void setLevel(Double level) {this.level = level;}

    public String getName(){return this.name;}
    public Double getLevel(){return level;}

}
