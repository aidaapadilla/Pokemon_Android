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
    private String attack1name;
    private String attack2name;

    public Pokemons(){};
    public Pokemons(String name, String type, Double level, Double maxHealth, Double actualHealth, Double probCapture, Double probAppear, String attack1name, String attack2name){
        this();
        setName(name);
        setType(type);
        setLevel(level);
        setMaxHealth(maxHealth);
        setActualHealth(actualHealth);
        setProbCapture(probCapture);
        setProbAppear(probAppear);
        setAttack1name(attack1name);
        setAttack2name(attack2name);
    }
    public void setName(String name){this.name=name;}
    public void setType(String type){this.type=type;}
    public void setLevel(Double level){this.level=level;}
    public void setMaxHealth(Double maxHealth){this.maxHealth=maxHealth;}
    public void setActualHealth(Double actualHealth){this.actualHealth=actualHealth;}
    //public void setAtackList(LinkedList<Attack> attackList){this.attackList = attackList;}
    public void setProbCapture(Double probCapture) {this.probCapture = probCapture;}
    public void setProbAppear(Double probAppear) {this.probAppear = probAppear;}
    public void setAttack1name(String name){this.attack1name=name;}
    public void setAttack2name(String name){this.attack2name=name;}

    public String getName(){return this.name;}
    public String getType(){return this.type;}
    public Double getLevel(){return this.level;}
    public Double getMaxHealth(){return this.maxHealth;}
    public Double getActualHealth(){return this.actualHealth;}
    public Double getProbCapture() {return probCapture;}
    public Double getProbAppear() {return probAppear;}
    public String getAttack1name(){return this.attack1name;}
    public String getAttack2name(){return this.attack2name;}
}
