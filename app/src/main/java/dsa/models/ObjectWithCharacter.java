package dsa.models;

public class ObjectWithCharacter {
    private Objects object;
    private Character character;


    public ObjectWithCharacter(){}
    public ObjectWithCharacter(Objects object, Character character){
        this();
        setCharacter(character);
        setObject(object);

    }

    public Character getCharacter() {return character;}
    public Objects getObject() {return object;}
    public void setCharacter(Character character) {this.character = character;}
    public void setObject(Objects object) {this.object = object;}
}
