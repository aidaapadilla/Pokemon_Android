package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

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

    static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    UserService API;

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(UserService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.LogIn);

        createAPI();

    }

    public void register_click(View view) {
        //try{
        TextView usernameText;
        usernameText = findViewById(R.id.usernameEditText);


        TextView userpassword;
        userpassword = findViewById(R.id.passwordEditText);
        username = usernameText.getText().toString();
        password = userpassword.getText().toString();
        doRegisterCall(username,password);
        if(userLogged!=null){
            //intent
        }
    }

    public void doRegisterCall(String username, String password){
        Call<User> call = API.login(username, password);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.isSuccessful()){
                    userLogged = response.body();
                }
                //else
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

/*
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

 */

}