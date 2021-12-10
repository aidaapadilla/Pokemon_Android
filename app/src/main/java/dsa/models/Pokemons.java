package dsa.models;

public class Pokemons {
    private String name;
    private String type;
    private Double level;
    //private String aspect;
    //LinkedList<Attack> attackList = new LinkedList<Attack>();
    private Double maxHealth;
    private Double actualHealth;
    private Double probCapture;
    private Double probAppear;
    private String attack1_name;
    private String attack2_name;

    public Pokemons(){};
    public Pokemons(String name, String type, Double level, Double maxHealth, Double actualHealth, Double probCapture, Double probAppear, String attack1_name, String attack2_name){
        this.name=name;
        this.type=type;
        this.level=level;
        //this.attackList = attackList;
        this.maxHealth=maxHealth;
        this.actualHealth=actualHealth;
        this.probCapture=probCapture;
        this.probAppear=probAppear;
        this.attack1_name=attack1_name;
        this.attack2_name=attack2_name;
    }
    public void setName(String name){this.name=name;}
    public void setType(String type){this.type=type;}
    public void setLevel(Double level){this.level=level;}
    public void setMaxHealth(Double maxHealth){this.maxHealth=maxHealth;}
    public void setActualHealth(Double actualHealth){this.actualHealth=actualHealth;}
    //public void setAtackList(LinkedList<Attack> attackList){this.attackList = attackList;}
    public void setProbCapture(Double probCapture) {this.probCapture = probCapture;}
    public void setProbAppear(Double probAppear) {this.probAppear = probAppear;}
    public void setAttack1_name(String name){this.attack1_name=name;}
    public void setAttack2_name(String name){this.attack2_name=name;}

    public String getName(){return this.name;}
    public String getType(){return this.type;}
    public Double getLevel(){return this.level;}
    public Double getMaxHealth(){return this.maxHealth;}
    public Double getActualHealth(){return this.actualHealth;}
    //public LinkedList<Attack> getAtackList(){return this.attackList;}
    public Double getProbCapture() {return probCapture;}
    public Double getProbAppear() {return probAppear;}
    public String getAttack1_name(){return this.attack1_name;}
    public String getAttack2_name(){return this.attack2_name;}
}