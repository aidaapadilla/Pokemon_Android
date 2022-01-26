package dsa.models;

public class ObjectWithCharacter {
    private String object;
    private Double price;
    private String character;


    public ObjectWithCharacter(){}
    public ObjectWithCharacter(String object,Double price, String character){
        this();
        setCharacter(character);
        setPrice(price);
        setObject(object);

    }

    public String getCharacter() {return character;}
    public String getObject() {return object;}

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public void setCharacter(String character) {this.character = character;}
    public void setObject(String object) {this.object = object;}
}
