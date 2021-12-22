package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Character;
import dsa.models.User;
import dsa.services.CharacterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    CharacterService API;

    ImageView avatarImg;
    ImageView mapImg;

    TextView characternameText;
    TextView pokemon1Text;
    TextView pokemon2Text;
    TextView pokemon3Text;
    TextView objectsText;
    TextView nameMapText;
    TextView pointsText;
    TextView moneyText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.profile);

        Intent intent = getIntent();

        characternameText = findViewById(R.id.characternameProfileText);
        pokemon1Text = findViewById(R.id.pokemon1ProfileText);
        pokemon2Text = findViewById(R.id.pokemon2ProfileText);
        pokemon3Text = findViewById(R.id.pokemon3ProfileText);
        objectsText = findViewById(R.id.objectsUserProfileText);
        nameMapText = findViewById(R.id.mapUserProfileText);
        pointsText = findViewById(R.id.pointsUserProfileText);
        moneyText = findViewById(R.id.moneyUserProfileText);
        avatarImg = findViewById(R.id.avatarProfileImage);
        mapImg = findViewById(R.id.mapProfileImage);

        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        String charactername = sharedPref.getString("charactername",null);

        createAPI();
        if(charactername!=null) {
            doAPIcall(charactername);
        }
    }

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

    public void doAPIcall(String name){
        Call<Character> call = API.getCharacter(name);
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if(response.isSuccessful()){
                    Character character = response.body();
                    setData(character);
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void setData(Character c){

        if(c.getAvatar().equals("may")) {
            avatarImg.setImageResource(R.drawable.may);
        }
        else if(c.getAvatar().equals("red")){
            avatarImg.setImageResource(R.drawable.red);
        }
        else if(c.getAvatar().equals("james")){
            avatarImg.setImageResource(R.drawable.james);
        }

        characternameText.setText(c.getName());
        if(c.getPokemon1name()!=null) {
            pokemon1Text.setText(c.getPokemon1name());
        }
        if(c.getPokemon2name()!=null) {
            pokemon2Text.setText(c.getPokemon2name());
        }
        if(c.getPokemon3name()!=null) {
            pokemon3Text.setText(c.getPokemon3name());
        }

        String text = null;
        if(c.getObject1name()!=null){
            if(text!=null) {
                text = text + c.getObject1name() + ",";
            }
            else{
                text = c.getObject1name() + ",";
            }
        }
        if(c.getObject2name()!=null){
            if(text!=null) {
                text = text + c.getObject2name() + ",";
            }
            else{
                text = c.getObject2name() + ",";
            }
        }
        if(c.getObject3name()!=null){
            if(text!=null){
                text = text + c.getObject3name() + ",";
            }
            else{
                text = c.getObject3name() + ",";
            }

        }
        nameMapText.setText(c.getMap());
        objectsText.setText(text);
        moneyText.setText(Double.toString(c.getMoney()));
        pointsText.setText(Double.toString(c.getPoints()));

        //falta mapa
    }

    public void optionsClick(View view){
        Intent intent = new Intent(ProfileActivity.this, OptionsActivity.class);
        startActivity(intent);
    }

    public void playClick(View v){
        //obrir la partida
    }

    public void storeButtonClick(View v){
        Intent intent = new Intent(ProfileActivity.this, AllObjectsActivity.class);
        startActivity(intent);
    }

}