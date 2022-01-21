package dsa.ejercicios_practica.pokemon_android;

import android.app.Notification;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
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

public class IntegrationUnity {

    public IntegrationUnity(){}

    CharacterService API;

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

    public static void getInformationUnity(String experience, String mapunity, String charactername){
        String points = experience;
        Log.i("Unity","The points received are "+points);
        String map = mapunity;
        Log.i("Unity","The map received is "+map);
        String name = charactername;
        Log.i("Unity", "Dentro Information");
        ActionsUnity p = new ActionsUnity();
        p.createAPI();
        p.getCharacterName("jllaveria",points,map);
        p.editCharacterCall();
    }
}
