package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Character;
import dsa.services.CharacterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    CharacterService API;

    Character character;

    TextView usernameText;
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
        String userID = intent.getStringExtra("id");



        usernameText = findViewById(R.id.usernameProfileText);
        pokemon1Text = findViewById(R.id.pokemon1ProfileText);
        pokemon2Text = findViewById(R.id.pokemon2ProfileText);
        pokemon3Text = findViewById(R.id.pokemon3ProfileText);
        objectsText = findViewById(R.id.objectsUserProfileText);
        nameMapText = findViewById(R.id.mapUserProfileText);
        pointsText = findViewById(R.id.pointsUserProfileText);
        moneyText = findViewById(R.id.moneyUserProfileText);

        createAPI();
        doAPIcall(userID);
        setData(character);
    }

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(CharacterService.class);
    }

    public void doAPIcall(String userId){
        Call<Character> call = API.getCharacter(userId);
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if(response.isSuccessful()){
                    character = response.body();
                }
            }

            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }

    public void setData(Character c){

        //falten les imatges del map i del avatar

        usernameText.setText(c.getNickname());
        pokemon1Text.setText(c.getPokemons().get(0).getName());
        pokemon2Text.setText(c.getPokemons().get(1).getName());
        pokemon3Text.setText(c.getPokemons().get(2).getName());
        int i = 0;
        String text = null;
        while(i < c.getObjects().size()){
            text = text + c.getObjects().get(i).getName();
        }
        objectsText.setText(text);
        //nameMapText.setText(c.getMap().getName());
        pointsText.setText(Double.toString(c.getPoints()));
    }

    public void playClick(View v){
        Button bt = (Button) v;
        //obrir la partida
    }

}