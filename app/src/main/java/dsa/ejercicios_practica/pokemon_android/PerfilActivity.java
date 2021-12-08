package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Map;
import dsa.models.Pokemon;
import dsa.models.User;
import dsa.models.Character;
import dsa.models.Object;
import dsa.services.UserService;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerfilActivity extends AppCompatActivity {
    User user;
    Character character;
    Map map;
    static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    UserService API;

    TextView usernameText;
    TextView pokemon1Text;
    TextView pokemon2Text;
    TextView pokemon3Text;
    TextView objectsText;
    TextView nameMapText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.perfil);
    /*
        Intent intent = getIntent();
        String userID = intent.getStringExtra("id");

     */

        createAPI();


        usernameText = findViewById(R.id.usernameProfileText);
        pokemon1Text = findViewById(R.id.pokemon1ProfileText);
        pokemon2Text = findViewById(R.id.pokemon2ProfileText);
        pokemon3Text = findViewById(R.id.pokemon3ProfileText);
        objectsText = findViewById(R.id.objectsUserProfileText);
        nameMapText = findViewById(R.id.mapUserProfileText);

        TextView points;
        points = findViewById(R.id.pointsUserProfileText);
        points.setText(String.valueOf(character.getPoints()));

        TextView money;
        money = findViewById(R.id.moneyUserProfileText);
        money.setText(String.valueOf(character.getMoney()));

        TextView avatar;
        avatar = findViewById(R.id.avatarText);
        avatar.setText(character.getNickname());

    }

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        //API = retrofit.create(CharacterService.class);
    }

}