package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
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

public class LogInActivity extends AppCompatActivity {
    //Necessitem la informació de la persona
    String username;
    String password;
    User userLogged;

    TextView usernameEditText;
    TextView passwordEditText;


    UserService API;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        usernameEditText = findViewById(R.id.characternameProfileText);
        passwordEditText = findViewById(R.id.passwordEditText);

        createAPI();

    }

    public void registerClick(View view){
        Intent intent = new Intent(view.getContext(), RegisterActivity.class);
        view.getContext().startActivity(intent);
    }

    public void login_click(View view) {

        username = usernameEditText.getText().toString();
        password = passwordEditText.getText().toString();
        doLoginCall(username,password);

        Intent intent = new Intent(LogInActivity.this, ProfileActivity.class);
        intent.putExtra("character name", userLogged.getCharactername());
        LogInActivity.this.startActivity(intent);
    }

    public void doLoginCall(String username, String password){

        Credentials credentials = new Credentials(username,password);
        Call<User> call = API.login(credentials);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                Log.i("joana",""+response.code());

                if (response.code()!=404){
                    User res = response.body();
                    setUserLogged(res);
                }
                else{
                    Context context = getApplicationContext();
                    String text = "User not found, try again!";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Context context = getApplicationContext();
                String text = "Error";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }

    public void setUserLogged(User userLogged){
        this.userLogged = userLogged;
    }

}