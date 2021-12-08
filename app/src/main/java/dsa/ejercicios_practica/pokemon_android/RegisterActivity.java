package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.LinkedList;
import java.util.List;

import dsa.models.Attack;
import dsa.models.Character;
import dsa.models.Object;
import dsa.models.Pokemon;
import dsa.models.User;
import dsa.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    UserService API;

    EditText emailText;
    EditText usernameText;
    EditText passwordText;
    EditText nicknameText;

    ImageView avatar1;
    ImageView avatar2;
    ImageView avatar3;

    ImageView pokemon1;
    ImageView pokemon2;
    ImageView pokemon3;

    RadioGroup pokemonGroup;
    RadioGroup avatarGroup;
    RadioButton pokemon1Bt;
    RadioButton pokemon2Bt;
    RadioButton pokemon3Bt;
    RadioButton avatar1Bt;
    RadioButton avatar2Bt;
    RadioButton avatar3Bt;

    String userId;

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(UserService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.register);
        Intent intent = getIntent();

        emailText = findViewById(R.id.emailEditText);
        usernameText = findViewById(R.id.usernameRegisterEditText);
        passwordText = findViewById(R.id.passwordRegisterEditText);
        nicknameText = findViewById(R.id.nicknameEditText);

        avatar1 = findViewById(R.id.avatar1);
        avatar2 = findViewById(R.id.avatar2);
        avatar3 = findViewById(R.id.avatar3);

        pokemon1 = findViewById(R.id.pokemon1);
        pokemon2 = findViewById(R.id.pokemon2);
        pokemon3 = findViewById(R.id.pokemon3);

        pokemonGroup = findViewById(R.id.pokemonRadioGroup);
        avatarGroup = findViewById(R.id.avatarRadioGroup);
        pokemon1Bt = findViewById(R.id.pokemon1RadioBt);
        pokemon2Bt = findViewById(R.id.pokemon2RadioBt);
        pokemon3Bt = findViewById(R.id.pokemon3RadioBt);
        avatar1Bt = findViewById(R.id.avatar1RadioBt);
        avatar2Bt = findViewById(R.id.avatar2RadioBt);
        avatar3Bt = findViewById(R.id.avatar3RadioBt);

        createAPI();
    }

    public void registerClick(View view){

        //String name= String.valueOf(avatarSelected);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String email = emailText.getText().toString();
        String nickname = nicknameText.getText().toString();

        Pokemon pokemon = null;

        if(pokemon1Bt.isChecked()){
            LinkedList<Attack> listAttacks = new LinkedList<Attack>();
            listAttacks.add(new Attack("ember","fire",3));
            listAttacks.add(new Attack("scratch","normal",2));
            pokemon = new Pokemon(1,"charmander",10,"fire",listAttacks);
        }
        else if(pokemon2Bt.isChecked()){
            LinkedList<Attack> listAttacks = new LinkedList<Attack>();
            listAttacks.add(new Attack("bubble","water",3));
            listAttacks.add(new Attack("tackle","normal",2));
            pokemon = new Pokemon(1,"squirtle",10,"water",listAttacks);
        }
        else if(pokemon3Bt.isChecked()){
            LinkedList<Attack> listAttacks = new LinkedList<Attack>();
            listAttacks.add(new Attack("razor leaf","grass",3));
            listAttacks.add(new Attack("take down","normal",2));
            pokemon = new Pokemon(1,"bulbasaur",10,"grass",listAttacks);
        }
        LinkedList<Pokemon> listPokemons = new LinkedList<Pokemon>();
        listPokemons.add(pokemon);

        Character character = new Character(username,0,0,listPokemons,null);
        User user = new User(username,password,email,character.getNickname());

        doAPIcall(user);

        Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
        intent.putExtra("id",userId);
        getBaseContext().startActivity(intent);
    }

    public void doAPIcall(User user){
        Call<String> call = API.register(user);
        call.enqueue(new Callback<String>() {
            @Override
            public void onResponse(Call<String> call, Response<String> response) {
                if (response.isSuccessful()){
                    userId = response.body();
                }
            }

            @Override
            public void onFailure(Call<String> call, Throwable t) {

            }
        });
    }



}