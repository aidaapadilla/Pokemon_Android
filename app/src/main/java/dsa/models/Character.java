package dsa.models;

public class Character {
    private String name;
    private Double money;
    private Double points;
    private String avatar;
    private String pokemon1_name;
    private String pokemon2_name;
    private String pokemon3_name;
    private String object1_name;
    private String object2_name;
    private String object3_name;
    //LinkedList<Pokemons> pokemons = new LinkedList<Pokemons>();
    //LinkedList<Object> objects = new LinkedList<Object>();

    public Character(){}
    public Character(String name, String avatar, Double money, Double points, String p1_name, String p2_name, String p3_name, String o1_name, String o2_name, String o3_name){
        this();
        setName(name);
        setMoney(money);
        setPoints(points);
        setPokemon1_name(p1_name);
        setPokemon2_name(p2_name);
        setPokemon3_name(p3_name);
        setObject1_name(o1_name);
        setObject2_name(o2_name);
        setObject3_name(o3_name);
        setAvatar(avatar);
        //setPokemons(pokemons);
        //setObjects(objects);
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setMoney(Double money){this.money=money;}
    public Double getMoney(){return this.money;}

    public void setPoints(Double points){this.points=points;}
    public Double getPoints(){return this.points;}

    //public void addPokemon(Pokemons pokemons){this.pokemons.add(pokemons);}
    //public void setPokemons(LinkedList<Pokemons> pokemons){this.pokemons=pokemons;}
    //public LinkedList<Pokemons> getPokemons(){return this.pokemons;}

    //public void setObjects(LinkedList<Object> objects){this.objects=objects;}
    //public LinkedList<Object> getObjects(){return this.objects;}

    public void setPokemon1_name(String p){this.pokemon1_name=p;}
    public String getPokemon1_name(){return this.pokemon1_name;}

    public void setPokemon2_name(String p){this.pokemon2_name=p;}
    public String getPokemon2_name(){return this.pokemon2_name;}

    public void setPokemon3_name(String p){this.pokemon3_name=p;}
    public String getPokemon3_name(){return this.pokemon3_name;}

    public void setObject1_name(String o){this.object1_name=o;}
    public String getObject1_name(){return this.object1_name;}

    public void setObject2_name(String o){this.object2_name=o;}
    public String getObject2_name(){return this.object2_name;}

    public void setObject3_name(String o){this.object3_name=o;}
    public String getObject3_name(){return this.object3_name;}

    public void addObject(Objects objects){
        if (object1_name==null)
            object1_name= objects.getName();
        else if (object2_name==null)
            object2_name= objects.getName();
        else if (object3_name==null)
            object3_name= objects.getName();
    }

    public String getAvatar(){
        return this.avatar;
    }

    public void setAvatar(String avatar){
        this.avatar = avatar;
    }


    /*
    public int compareTo(Character c)
    {
        int res = (int) (this.getPoints()-c.getPoints());
        return res;
    }
    */
}
