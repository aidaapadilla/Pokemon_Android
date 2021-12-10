package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

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

    User userRegistered;

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
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
        /*
        String img="";
        Pokemon pokemon = null;


        if(pokemon1Bt.isChecked()){
            LinkedList<Attack> listAttacks = new LinkedList<Attack>();
            listAttacks.add(new Attack("ember","fire",3));
            listAttacks.add(new Attack("scratch","normal",2));
            pokemon = new Pokemon("charmander",1,10,"fire",listAttacks,img);
        }
        else if(pokemon2Bt.isChecked()){
            LinkedList<Attack> listAttacks = new LinkedList<Attack>();
            listAttacks.add(new Attack("bubble","water",3));
            listAttacks.add(new Attack("tackle","normal",2));
            pokemon = new Pokemon("squirtle",1,10,"water",listAttacks,img);
        }
        else if(pokemon3Bt.isChecked()){
            LinkedList<Attack> listAttacks = new LinkedList<Attack>();
            listAttacks.add(new Attack("razor leaf","grass",3));
            listAttacks.add(new Attack("take down","normal",2));
            pokemon = new Pokemon("bulbasaur",1,10,"grass",listAttacks,img);
        }
        LinkedList<Pokemon> listPokemons = new LinkedList<Pokemon>();
        listPokemons.add(pokemon);

        Character character = new Character(username,0,0,listPokemons,null);
        */
        User user = new User(username,password,email,nickname);

        doAPIcall(user);

        /*
        Intent intent = new Intent(getBaseContext(), ProfileActivity.class);
        intent.putExtra("id",userId);
        getBaseContext().startActivity(intent);

         */
    }

    public void doAPIcall(User user){
        Call<User> call = API.register(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("joana",""+response.code());
                if (response.code()!=404){
                    userRegistered = response.body();
                    Context context = getApplicationContext();
                    String text = "User registered!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    Context context = getApplicationContext();
                    String text = "Error in register";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Context context = getApplicationContext();
                String text = "Error in connectivity";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }



}