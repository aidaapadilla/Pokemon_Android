package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import dsa.ejercicios_practica.pokemon_android.exeptions.NullTextView;
import dsa.ejercicios_practica.pokemon_android.exeptions.UserNotFound;
import dsa.ejercicios_practica.pokemon_android.exeptions.*;
import dsa.models.User;

public class LogInActivity extends AppCompatActivity {
    //Necessitem la informació de la persona
    String username;
    String password;
    User t;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.LogIn);

    }
    public void register_click(View view){
        //try{
            TextView usernameText;
            usernameText = findViewById(R.id.usernameText);


            TextView userpassword;
            userpassword = findViewById(R.id.passwordText);
            username = usernameText.getText().toString();
            password = userpassword.getText().toString();

            //funcio per trobar a la base de dades si correspon
            if(t.CheckUser(username,password)==0)
            {
                //the user doesn't exist
            }
            if(t.CheckUser(username,password)==1)
            {
                //the user exists open the new activity
            }

        //}
        //catch(NullTextView) {

        //}

    }

}