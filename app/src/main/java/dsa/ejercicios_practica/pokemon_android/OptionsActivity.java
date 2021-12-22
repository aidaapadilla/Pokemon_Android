package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Character;
import dsa.services.CharacterService;
import dsa.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class OptionsActivity extends AppCompatActivity {

    UserService API;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_options);

        Intent intent = getIntent();
    }

    public void pokedexClick(View v){
        Intent intent = new Intent(OptionsActivity.this, PokedexActivity.class);
        startActivity(intent);
    }

    public void mapsClick(View v){
        //intent maps
    }

    public void rankingClick(View v){
        //intent ranking
    }

    public void editClick(View v){
        Intent intent = new Intent(this, EditProfileActivity.class);
        startActivity(intent);
    }

    public void deleteClick(View v){
        createAPI();

    }

    public void closeClick(View v){
        Intent intent = new Intent(OptionsActivity.this, ProfileActivity.class);
        startActivity(intent);
    }

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
}