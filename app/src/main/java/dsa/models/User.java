package dsa.models;

import android.widget.TextView;

public class User {
    //private String id;
    private String name;
    private String password;
    private String email;
    private String charactername;
    //private Character character;

    public User(){

    }
    public User(String name, String password, String email, String charactername){
        this();
        //setId(id);
        setName(name);
        setPassword(password);
        setEmail(email);
        setCharactername(charactername);
    }
    public User(String username, String password){
        this();
        setName(username);
        setPassword(password);
    }

    public void setName(String name){this.name=name;}
    public String getName(){return this.name;}

    public void setPassword(String password){this.password=password;}
    public String getPassword(){return this.password;}

    //public void setId(String id){this.id=id;}
    //public String getId(){return this.id;}

    public void setEmail(String email){this.email=email;}
    public String getEmail(){return this.email;}

    //public Character getCharacter(){return m.searchCharacter(this,this.character_name);}

    public void setCharactername(String c){this.charactername =c;}
    public String getCharactername(){return this.charactername;}
    /*
    public int compareTo(User c)
    {
        int res = (int) (this.getCharacter().getPoints()-c.getCharacter().getPoints());
        return res;
    }
    */
}
