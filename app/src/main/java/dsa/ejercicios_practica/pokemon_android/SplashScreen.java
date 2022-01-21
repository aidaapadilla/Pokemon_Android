package dsa.ejercicios_practica.pokemon_android;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.os.Handler;
import dsa.ejercicios_practica.pokemon_android.LogInActivity;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.WindowManager;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.Credentials;
import dsa.models.User;
import dsa.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;


public class SplashScreen extends Activity {

    private final int DURACION_SPLASH = 4500;
    Context context;
    SharedPreferences sharedPref;
    String username;
    String password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {
            public void run() {
                username = "";
                password= "";

                getUserLogged(); //Comentar si se quiere pasar por la parte de login y register

                if (username.equals(password)) {
                    Intent intent = new Intent(SplashScreen.this, LogInActivity.class);
                    startActivity(intent);
                    finish();
                }
                else {
                    Intent intent = new Intent(SplashScreen.this, ProfileActivity.class);
                    startActivity(intent);
                    finish();

                }
            };
        }, DURACION_SPLASH);
    }

    public void getUserLogged() {
        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        username = sharedPref.getString("name", "No information found");
        password = sharedPref.getString("password", "No information found");
        Toast.makeText(getApplicationContext(), "User found: " + username, Toast.LENGTH_LONG);
    }

}