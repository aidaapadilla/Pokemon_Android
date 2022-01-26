package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.unity3d.player.UnityPlayerActivity;

import dsa.models.Character;
import dsa.services.CharacterService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class ProfileActivity extends AppCompatActivity {

    static final String BASE_URL = "http://147.83.7.204:8080";
    CharacterService API;

    ImageView avatarImg;
    ImageView mapImg;

    String charactername;
    int avatar;

    Character character;

    View viewprofile;

    ProgressBar progressBar;
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
                if(response.code()==201){
                    character = response.body();
                    setData(character);}
                else{
                    Context context = getApplicationContext();
                    String text = "Error avatar no encontrado";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text + response.code(), duration);
                    toast.show();
                    progressBar.setVisibility(viewprofile.INVISIBLE);
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
            avatar = 2;
        }
        else if(c.getAvatar().equals("red")){
            avatarImg.setImageResource(R.drawable.red);
            avatar = 1;
        }
        else if(c.getAvatar().equals("james")){
            avatarImg.setImageResource(R.drawable.james);
            avatar = 0;
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
        if(c.getMap().equals("HomeTown")){
            mapImg.setImageResource(R.drawable.hometown);
        }
        else if(c.getMap().equals("Route1")){
            mapImg.setImageResource(R.drawable.route);
        }
        else if(c.getMap().equals("Gym")){
            mapImg.setImageResource(R.drawable.gym);
        }

    }

    public void optionsClick(View view){
        doAPIcall(charactername);
        Intent intent = new Intent(ProfileActivity.this, OptionsActivity.class);
        startActivity(intent);
    }

    public void playClick(View v){
        Intent intent = new Intent(this, UnityPlayerActivity.class);
        intent.putExtra("charactername",charactername);
        intent.putExtra("avatarname",avatar);
        if (character.getPokemon1name()!=null){
            intent.putExtra("pokemon1",character.getPokemon1name());
        }
        if (character.getPokemon2name()!=null){
            intent.putExtra("pokemon2",character.getPokemon2name());
        }
        if (character.getPokemon3name()!=null){
            intent.putExtra("pokemon3",character.getPokemon3name());
        }
        if (character.getObject1name()!=null){
            intent.putExtra("object1",character.getObject1name());
        }
        if (character.getObject2name()!=null){
            intent.putExtra("object2",character.getObject2name());
        }
        if (character.getObject3name()!=null){
            intent.putExtra("object3",character.getObject3name());
        }
        startActivity(intent);
    }

    public void storeButtonClick(View view){
        //viewprofile=view;
        //progressBar.setVisibility(viewprofile.VISIBLE);
        Intent intent = new Intent(ProfileActivity.this, AllObjectsActivity.class);
        startActivity(intent);
    }


}