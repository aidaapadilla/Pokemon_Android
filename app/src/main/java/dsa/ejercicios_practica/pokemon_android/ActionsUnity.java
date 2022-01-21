package dsa.ejercicios_practica.pokemon_android;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Character;
import dsa.services.CharacterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ActionsUnity {

    Character character;
    String charactername;
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

    public void getCharacterName(String name, String points, String map){
        charactername = name;
        Log.i("Unity","I'm going to find "+charactername);
        Call<Character> call = API.getCharacter(name);
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                Log.i("Unity","The response code is "+String.valueOf(response.code()));
                if(response.code()==201){
                    character = response.body();
                    Log.i("Unity", "The name of the user is "+character.getName());
                    Log.i("Unity", "I'm going to add the points "+points);
                    Double pointstoadd = Double.parseDouble(points);
                    character.setPoints(Double.parseDouble(points));
                    Log.i("Unity", "I'm going to add the map");
                    character.setMap(map);
                    editCharacterCall();
                }
                else{
                    //String text = "Error avatar no encontrado";
                    //int duration = Toast.LENGTH_SHORT;

                    //Toast toast = Toast.makeText(, text + response.code(), duration);
                    //toast.show();
                }
            }
            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void editValues(String points, String map){
        Log.i("Unity", "I'm going to add the points "+points);
        character.setPoints(Double.parseDouble(points));
        Log.i("Unity", "I'm going to add the map");
        character.setMap(map);
        editCharacterCall();
    }

    public void editCharacterCall(){
        Call<Character> call = API.updateCharacter(character);
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                if(response.code()==201){
                    Log.i("Character ","Character editted");
                }
                else{
                    //String text = "Error avatar no encontrado";
                    //int duration = Toast.LENGTH_SHORT;

                    //Toast toast = Toast.makeText(, text + response.code(), duration);
                    //toast.show();
                }
            }
            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}
