package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Character;
import dsa.models.User;
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
        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        String username = sharedPref.getString("name",null);
        doAPIdeleteCall(username);
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

    public void doAPIdeleteCall(String name){
        Call<User> call = API.deleteUser(name);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if(response.isSuccessful()){
                    SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
                    sharedPref.edit().clear();
                    sharedPref.edit().commit();
                    Intent intent = new Intent(OptionsActivity.this, LogInActivity.class);
                    startActivity(intent);
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
            }
        });

    }
}