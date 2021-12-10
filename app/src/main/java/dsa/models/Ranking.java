package dsa.models;


import java.util.Comparator;

public class Ranking implements Comparator<Character> {

    public int compare(Character c1, Character c2){

        return (int)(c1.getPoints() - c2.getPoints());
    }

}