package dsa.models;

public class Object {
    private String name;
    private double price;
    private String type;

    public Object(){}
    public Object(String name, double price, String type){
        this();
        setName(name);
        setPrice(price);
        setType(type);
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setPrice(double price){this.price=price;}
    public double getPrice(){return this.price;}

    public void setType(String type){this.type=type;}
    public String getType(){return this.type;}
}
