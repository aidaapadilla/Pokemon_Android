package dsa.models;

import android.widget.TextView;

public class User {
    private String username;
    private String password;
    private String id;
    private String email;
    private Character character;
    private String characterName;

    public User(){}
    public User(String username, String password, String email, String character_name){
        this();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setCharacterName(character_name);
    }
    public User(String username, String password){
        this();
        setUsername(username);
        setPassword(password);
    }


    public int CheckUser(String username,String password){ //0 no existeix 1 si existeix
        return 0; //if it doesn't exist
    }


    public void setUsername(String username){this.username=username;}
    public String getUsername(){return this.username;}

    public void setPassword(String password){this.password=password;}
    public String getPassword(){return this.password;}

    public void setId(String id){this.id=id;}
    public String getId(){return this.id;}

    public void setEmail(String email){this.email=email;}
    public String getEmail(){return this.email;}

    public void setCharacter(Character c){this.character=c;}
    public Character getCharacter(){return this.character;}
    public void setCharacterName(String c){this.characterName=c;}
    public String getCharacterName(){return this.characterName;}

}
