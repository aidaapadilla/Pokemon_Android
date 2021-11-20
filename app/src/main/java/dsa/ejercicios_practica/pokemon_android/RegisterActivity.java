package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import dsa.models.Pokemon;
import dsa.models.User;

public class RegisterActivity extends AppCompatActivity {
    int pokemonSelected=0;
    int avatarSelected=0;
    String email;
    String username;
    String password;
    Pokemon pokemon;
    Character avatar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Register);
    }

    public void register(View view){
        TextView emailText;
        emailText = findViewById(R.id.emailText);
        email = (String) emailText.getText();

        TextView usernameText;
        usernameText = findViewById(R.id.UserName_text);
        username = (String) usernameText.getText();

        TextView passwordText;
        passwordText = findViewById(R.id.Password_text);
        password = (String) passwordText.getText();

        //Buscar el avatar X i el pokemon X


        User usuari = new User(username,password,email,avatar);
    }
    public int selectAvatar1(View view){
        if(avatarSelected==0) { avatarSelected = 1; }
        return avatarSelected;
    }
    public int selectAvatar2(View view){
        if(avatarSelected==0) {avatarSelected = 2;}
        return avatarSelected;
    }
    public int selectAvatar3(View view){
        if(avatarSelected==0) {avatarSelected = 3;}
        return avatarSelected;
    }

    public int selectPokemon1(View view){
        if(pokemonSelected==0) {pokemonSelected = 1;}
        return pokemonSelected;
    }
    public int selectPokemon2(View view){
        if(pokemonSelected==0) {pokemonSelected = 2;}
        return pokemonSelected;
    }
    public int selectPokemon3(View view){
        if(pokemonSelected==0) {pokemonSelected = 3;}
        return pokemonSelected;
    }
}