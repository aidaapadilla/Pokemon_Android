package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import dsa.models.User;
import dsa.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisterActivity extends AppCompatActivity {

    UserService API;

    EditText emailText;
    EditText usernameText;
    EditText passwordText;
    EditText nicknameText;

    User userRegistered;

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
        setContentView(R.layout.register);
        Intent intent = getIntent();

        emailText = findViewById(R.id.emailEditText);
        usernameText = findViewById(R.id.usernameRegisterEditText);
        passwordText = findViewById(R.id.passwordRegisterEditText);
        nicknameText = findViewById(R.id.nicknameEditText);

        createAPI();
    }

    public void registerClick(View view){

        //String name= String.valueOf(avatarSelected);

        String username = usernameText.getText().toString();
        String password = passwordText.getText().toString();
        String email = emailText.getText().toString();
        String nickname = nicknameText.getText().toString();

        User user = new User(username,password,email,nickname);

        doAPIcall(user);


        Intent intent = new Intent(view.getContext(), CharacterCreationActivity.class);
        intent.putExtra("character name",userRegistered.getCharactername());
        view.getContext().startActivity(intent);
    }

    public void doAPIcall(User user){
        Call<User> call = API.register(user);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                if (response.code()!=404){
                    userRegistered = response.body();
                }
                else{
                    Context context = getApplicationContext();
                    String text = "Error in register";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {
                t.printStackTrace();
                Context context = getApplicationContext();
                String text = "Error in connectivity";
                int duration = Toast.LENGTH_SHORT;

                Toast toast = Toast.makeText(context, text, duration);
                toast.show();
            }
        });
    }



}