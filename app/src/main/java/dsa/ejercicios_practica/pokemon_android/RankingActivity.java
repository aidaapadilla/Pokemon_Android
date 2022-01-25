package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dsa.models.Character;
import dsa.models.Pokemons;
import dsa.services.CharacterService;
import dsa.services.PokemonService;
import dsa.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RankingActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterRanking mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    CharacterService API;
    List<Character> characterList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ranking);

        Intent intent = getIntent();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterRanking();
        recyclerView.setAdapter(mAdapter);

        createAPI();
        doApiCall();
    }

    public void createAPI(){
        Gson gson = new GsonBuilder()
                .setLenient()
                .create();

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(UserService.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(CharacterService.class);
    }

    public void doApiCall(){
        Call<List<Character>> call = API.getRanking();
        call.enqueue(new Callback<List<Character>>() {
            @Override
            public void onResponse(Call<List<Character>> call, Response<List<Character>> response) {
                if(response.code()==200) {
                    characterList=response.body();
                    mAdapter.setData(characterList);
                }
                else {
                    Toast toast = Toast.makeText(RankingActivity.this,"Ranking no encontrado",Toast.LENGTH_SHORT);
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    toast.show();
                }}

            @Override
            public void onFailure(Call<List<Character>> call, Throwable t) {
                Toast toast = Toast.makeText(RankingActivity.this,"ERROR DE CONEXIÓN, no se ha podido realizar la petición.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void exitClick(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
}