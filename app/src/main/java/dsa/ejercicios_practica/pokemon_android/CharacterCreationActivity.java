package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Character;
import dsa.services.CharacterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterCreationActivity extends AppCompatActivity {

    CharacterService API;

    String name;

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

    TextView title;

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(CharacterService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(CharacterService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_character_creation);

        avatar1 = findViewById(R.id.avatar1imageView);
        avatar2 = findViewById(R.id.avatar2imageView);
        avatar3 = findViewById(R.id.avatar3imageView);

        pokemon1 = findViewById(R.id.pokemon1imageView);
        pokemon2 = findViewById(R.id.pokemon2imageView);
        pokemon3 = findViewById(R.id.pokemon3imageView);

        pokemonGroup = findViewById(R.id.pokemonRadioGroup);
        avatarGroup = findViewById(R.id.avatarRadioGroup);
        pokemon1Bt = findViewById(R.id.pokemon1RadioBt);
        pokemon2Bt = findViewById(R.id.pokemon2RadioBt);
        pokemon3Bt = findViewById(R.id.pokemon3RadioBt);
        avatar1Bt = findViewById(R.id.avatar1RadioBt);
        avatar2Bt = findViewById(R.id.avatar2RadioBt);
        avatar3Bt = findViewById(R.id.avatar3RadioBt);

        title = findViewById(R.id.nameCharacterText);

        Intent intent = getIntent();
        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        name = sharedPref.getString("charactername",null);
        String text = "Hello "+name+"!";
        title.setText(text);

        createAPI();
    }

    public void goClick(View view){
        String pokemon;
        String avatar;

        if(pokemon1Bt.isChecked()){
            pokemon = "Charmander";
        }
        else if(pokemon2Bt.isChecked()){
            pokemon = "Squirtle";
        }
        else if(pokemon3Bt.isChecked()){
            pokemon = "Bulbasaur";
        }
        else{
            pokemon = "Charmander";
        }

        if(avatar1Bt.isChecked()){
            avatar = "may";
        }
        else if(avatar2Bt.isChecked()){
            avatar = "red";
        }
        else if(avatar3Bt.isChecked()){
            avatar = "james";
        }
        else{
            avatar = "may";
        }
        Character character = new Character(name,avatar,500.0,0.0,pokemon,null,null,null,null,null);
        Log.d("CharacterCreation",pokemon);
        doAPIcall(character);

    }

    public void doAPIcall(Character character){
        Call<Character> call = API.newCharacter(character);
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if (response.code()==201) {
                    openProfileActivity();
                }
                else if(response.code()==500){
                    Context context = getApplicationContext();
                    String text = "Error in creating the character";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }else if(response.code()==502) {
                    Context context = getApplicationContext();
                    String text = "Error in character form";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
                else{
                    Context context = getApplicationContext();
                    String text = "Error";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text + response.code(), duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                t.printStackTrace();
                Context context = getApplicationContext();
                String text = "Error in connectivity";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public void openProfileActivity(){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }

}