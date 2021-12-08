package dsa.models;

public class Object {
    private String name;
    private double price;
    private String description;
    private String image;

    public Object(){}
    public Object(String name, double price, String description,String image){
        this();
        setName(name);
        setPrice(price);
        setDescription(description);
        setImage(image);
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setPrice(double price){this.price=price;}
    public double getPrice(){return this.price;}

    public void setDescription(String description){this.description=description;}
    public String getDescription(){return this.description;}

    private void setImage(String image) { this.image=image;}
    public String getImage(){return this.image;}
}
