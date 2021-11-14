package dsa.models;

import java.util.LinkedList;

public class Character {
    private String nickname;
    private double money;
    private double points;
    //private String avatar;
    LinkedList<Pokemon> pokemons = new LinkedList<Pokemon>();
    LinkedList<Object> objects = new LinkedList<Object>();

    public Character(){}
    public Character(String nickname,double money,double points,LinkedList<Pokemon> pokemons,LinkedList<Object> objects){
        this();
        setNickname(nickname);
        setMoney(money);
        setPoints(points);
        setPokemons(pokemons);
        setObjects(objects);
    }

    public void setNickname(String nickname){this.nickname=nickname;}
    public String getNickname(){return this.nickname;}

    public void setMoney(double money){this.money=money;}
    public double getMoney(){return this.money;}

    public void setPoints(double points){this.points=points;}
    public double getPoints(){return this.points;}

    public void addPokemon(Pokemon pokemon){this.pokemons.add(pokemon);}
    public void setPokemons(LinkedList<Pokemon> pokemons){this.pokemons=pokemons;}
    public LinkedList<Pokemon> getPokemons(){return this.pokemons;}

    public void setObjects(LinkedList<Object> objects){this.objects=objects;}
    public LinkedList<Object> getObjects(){return this.objects;}

    public void addObject(Object object){this.objects.add(object);}
    public int compareTo(Character c)
    {
        int res = (int) (this.getPoints()-c.getPoints());
        return res;
    }
}
