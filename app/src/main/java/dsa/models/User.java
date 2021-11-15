package dsa.models;

public class User {
    private String username;
    private String password;
    private String id;
    private String email;
    private Character character;

    public User(){}
    public User(String username, String password, String email, Character character){
        this();
        setUsername(username);
        setPassword(password);
        setEmail(email);
        setCharacter(character);
    }
    public User(String username, String password){
        this();
        setUsername(username);
        setPassword(password);
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

}
