package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Character;
import dsa.models.Pokemons;
import dsa.services.CharacterService;
import dsa.services.UserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class CharacterChoiceActivity extends AppCompatActivity {

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
        setContentView(R.layout.activity_character_choice);

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

        createAPI();
    }

    protected void goClick(View view){
        String pokemon=null;
        String avatar = null;

        if(pokemon1Bt.isChecked()){
            pokemon = "Charmander";
        }
        else if(pokemon2Bt.isChecked()){
            pokemon = "Squirtle";
        }
        else if(pokemon3Bt.isChecked()){
            pokemon = "Bulbasaur";
        }

        if(avatar1Bt.isChecked()){
            avatar = "May";
        }
        else if(avatar2Bt.isChecked()){
            avatar = "Red";
        }
        else if(avatar3Bt.isChecked()){
            avatar = "James";
        }
        Character character = new Character(name,0.,0.,pokemon,null,null,null,null,null);
    }

}