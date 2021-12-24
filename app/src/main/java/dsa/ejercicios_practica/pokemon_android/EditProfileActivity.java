package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
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
public class EditProfileActivity extends AppCompatActivity{

    UserService API;

    TextView emailText;
    TextView password1Text;
    TextView password2Text;

    Context context;
    String username;
    String email;
    String password;
    String charactername;

    String password1;
    String password2;

    ProgressBar progressBar;

    View viewEditProfile;

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        username = sharedPref.getString("name",null);
        email = sharedPref.getString("email",null);
        password = sharedPref.getString("password",null);
        charactername = sharedPref.getString("charactername", null);

        emailText.setText(email);

        API = retrofit.create(UserService.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        Intent intent = getIntent();

        emailText = findViewById(R.id.emailText);
        password1Text = findViewById(R.id.password1Text);
        password2Text = findViewById(R.id.password2Text);
        progressBar = findViewById(R.id.progressBarEditProfile);
        context=this;

        createAPI();

    }

    public void updateClick(View view) {
        viewEditProfile =view;
        email = emailText.getText().toString();
        password1 = password1Text.getText().toString();
        password2 = password2Text.getText().toString();
        progressBar.setVisibility(viewEditProfile.VISIBLE);
        if (password1.equals(password2)==true){
            User u = new User(username, password1, email,charactername);
            Call<User> call = API.update(u);
            call.enqueue(new Callback<User>() {
                @Override
                public void onResponse(Call<User> call, Response<User> response) {
                    Log.i("update",""+response.code());

                    if (response.code()!=404){
                        Log.i("response code=",""+response.code());
                        updateUserLogged(u);
                        String text = "Update made correctly";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        progressBar.setVisibility(viewEditProfile.INVISIBLE);
                    }
                    else{
                        Context context = getApplicationContext();
                        String text = "Information could not be changed";
                        int duration = Toast.LENGTH_SHORT;
                        Toast toast = Toast.makeText(context, text, duration);
                        toast.show();
                        progressBar.setVisibility(viewEditProfile.INVISIBLE);
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
                    progressBar.setVisibility(viewEditProfile.INVISIBLE);
                }
            });
        }
        String text = "Passwords are different";
        int duration = Toast.LENGTH_SHORT;
        Toast toast = Toast.makeText(context, text, duration);
        toast.show();
        progressBar.setVisibility(viewEditProfile.INVISIBLE);
    }

    public void exitClick(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }

    public void updateUserLogged(User user){
        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPref.edit();
        editor.putString("password",user.getPassword());
        editor.putString("email",user.getEmail());
        editor.commit();
    }
    /*
    public void getUserEmail(User user){
        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        SharedPreferences.Editor ;
        editor.putString("name",user.getName());
        editor.putString("password",user.getPassword());
        editor.putString("charactername",user.getCharactername());
        editor.commit();
    }

     */
}
