package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import dsa.models.Map;
import dsa.models.Pokemon;
import dsa.models.User;
import dsa.models.Character;
import dsa.models.Object;

public class PerfilActivity extends AppCompatActivity {
    User user;
    Pokemon pokemon1;
    Pokemon pokemon2;
    Pokemon pokemon3;
    Character character;
    Object object2;
    Map map;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.Perfil);

        //Hem de buscar quin usuari ha entrat a aquesta finestra
        TextView usernameText;
        usernameText = findViewById(R.id.usernameEditText);

        TextView pokemon1;
        pokemon1 = findViewById(R.id.pokemon1Text);
        pokemon1.setText(this.pokemon1.getName());
        
        TextView pokemon2;
        pokemon2 = findViewById(R.id.pokemon2Text);
        pokemon2.setText(this.pokemon2.getName());
        
        TextView pokemon3;
        pokemon3 = findViewById(R.id.pokemon3Text);
        pokemon3.setText(this.pokemon3.getName());

        TextView objects;
        objects = findViewById(R.id.objectText);
        objects.setText(this.object2.getName());

        TextView nameMap;
        nameMap = findViewById(R.id.mapText);
        nameMap.setText(this.map.getName());

        TextView points;
        points = findViewById(R.id.pointsText);
        points.setText(String.valueOf(character.getPoints()));

        TextView money;
        money = findViewById(R.id.moneyText);
        money.setText(String.valueOf(character.getMoney()));

        TextView avatar;
        avatar = findViewById(R.id.avatarText);
        avatar.setText(character.getNickname());
    }

}