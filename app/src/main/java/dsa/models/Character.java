package dsa.models;

public class Character {
    private String name;
    private String username;
    private Double money;
    private Double points;
    private String map;
    private String avatar;
    private String pokemon1name;
    private String pokemon2name;
    private String pokemon3name;
    private String object1name;
    private String object2name;
    private String object3name;
    private String id;
    //LinkedList<Pokemons> pokemons = new LinkedList<Pokemons>();
    //LinkedList<Object> objects = new LinkedList<Object>();

    public Character(){}

    public Character(String name, String username, String avatar, String id, String map, Double money, Double points, String p1_name, String p2_name, String p3_name, String o1_name, String o2_name, String o3_name){
        this();
        setName(name);
        setUsername(username);
        setId(id);
        setMoney(money);
        setPoints(points);
        setPokemon1name(p1_name);
        setPokemon2name(p2_name);
        setPokemon3name(p3_name);
        setObject1name(o1_name);
        setObject2name(o2_name);
        setObject3name(o3_name);
        setAvatar(avatar);
        setMap(map);
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

    public void setPokemon1name(String p){this.pokemon1name=p;}
    public String getPokemon1name(){return this.pokemon1name;}

    public void setPokemon2name(String p){this.pokemon2name=p;}
    public String getPokemon2name(){return this.pokemon2name;}

    public void setPokemon3name(String p){this.pokemon3name=p;}
    public String getPokemon3name(){return this.pokemon3name;}

    public void setObject1name(String o){this.object1name=o;}
    public String getObject1name(){return this.object1name;}

    public void setObject2name(String o){this.object2name=o;}
    public String getObject2name(){return this.object2name;}

    public void setObject3name(String o){this.object3name=o;}
    public String getObject3name(){return this.object3name;}

    public void addObject(Objects objects){
        if (object1name==null)
            object1name= objects.getName();
        else if (object2name==null)
            object2name= objects.getName();
        else if (object3name==null)
            object3name= objects.getName();
    }

    public String getAvatar(){return this.avatar;}

    public void setAvatar(String avatar){this.avatar = avatar;}

    public String getMap(){
        return this.map;
    }

    public void setMap(String map) {
        this.map = map;
    }

    public String getUsername(){
        return this.username;
    }
    public void setUsername(String username){
        this.username = username;
    }

    public String getId(){return this.id;}
    public void setId(String id){this.id=id;}

    /*
    public int compareTo(Character c)
    {
        int res = (int) (this.getPoints()-c.getPoints());
        return res;
    }
    */
}