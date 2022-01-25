package dsa.ejercicios_practica.pokemon_android;

import static dsa.services.CharacterService.BASE_URL;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dsa.models.Character;
import dsa.models.ObjectWithCharacter;
import dsa.models.Objects;
import dsa.services.ObjectService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllObjectsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterObject mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    ObjectService API;
    List<Objects>objectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_objects);

        layoutManager = new LinearLayoutManager(this);
        mAdapter = new AdapterObject();
        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(mAdapter);

        SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
        String charactername = sharedPref.getString("charactername",null);

        createAPI();
        doApiCall();
    }

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(ObjectService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(ObjectService.class);
    }

    //Devuelve la lista con todos los objetos
    public void doApiCall(){
        Call<List<Objects>> call = API.getObjects();
        call.enqueue(new Callback<List<Objects>>() {
            @Override
            public void onResponse(Call<List<Objects>> call, Response<List<Objects>> response) {

                if(!response.body().isEmpty()) {
                    objectList = response.body();
                    mAdapter.setData(objectList);

                    SharedPreferences sharedPref = getSharedPreferences("userlogged", Context.MODE_PRIVATE);
                    mAdapter.SetOnItemClickListener(new AdapterObject.OnItemClickListener() {
                        @Override
                        public void onItemClick(int position) {
                            buyAnItem(position,sharedPref.getString("charactername",null));
                        }
                    });
                    //tracksList.forEach(track -> System.out.println(track.title));
                }
                else {
                    Intent intent = new Intent(AllObjectsActivity.this, ProfileActivity.class);
                    startActivity(intent);
                    Toast toast = Toast.makeText(dsa.ejercicios_practica.pokemon_android.AllObjectsActivity.this,"Lista de objetos vacía",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Objects>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    public void buyAnItem(int position, String name){

        Character ch = new Character(name,"","","",0.0,0.0,"","","","","","");

        ObjectWithCharacter objectWithCharacter = new ObjectWithCharacter(objectList.get(position),ch);
        Call<Character> call = API.buyObject(objectWithCharacter);
        call.enqueue(new Callback<Character>() {
            @Override
            public void onResponse(Call<Character> call, Response<Character> response) {
                Toast toast;
                Log.d("Shop",response.message());
                if(response.code() == 201){
                    toast = Toast.makeText(AllObjectsActivity.this, "Compra realizada", Toast.LENGTH_SHORT);
                }
                else{
                    toast = Toast.makeText(AllObjectsActivity.this, "Error en la compra", Toast.LENGTH_SHORT);
                }
                toast.show();
            }
            @Override
            public void onFailure(Call<Character> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
    @Override
    public void onBackPressed() {
        // Botón atrás hacia el perfil
        Intent intent = new Intent(AllObjectsActivity.this, ProfileActivity.class);
        startActivity(intent);
        final Dialog dialog = new Dialog(this);
        dialog.setCanceledOnTouchOutside(true);
        dialog.setCancelable(true);
        dialog.dismiss();
        Intent returnIntent = new Intent();
        setResult(Activity.RESULT_OK, returnIntent);
        finish();
        dialog.show();
    }

}