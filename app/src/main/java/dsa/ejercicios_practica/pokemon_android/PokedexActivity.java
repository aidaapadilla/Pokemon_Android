package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dsa.models.Basepokemon;
import dsa.models.Pokemons;
import dsa.services.PokemonService;
import dsa.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PokedexActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPokemon mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    PokemonService API;
    List<Basepokemon> pokemonList;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pokedex);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterPokemon();
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

        API = retrofit.create(PokemonService.class);
    }

    public void doApiCall(){
        Call<List<Basepokemon>> call = API.getPokemons();
        call.enqueue(new Callback<List<Basepokemon>>() {
            @Override
            public void onResponse(Call<List<Basepokemon>> call, Response<List<Basepokemon>> response) {
                if(response.code()==200) {
                    pokemonList=response.body();
                    mAdapter.setData(pokemonList);
                }
                else {
                    Toast toast = Toast.makeText(PokedexActivity.this,"Lista de pokemons vacía",Toast.LENGTH_SHORT);
                    Context context = getApplicationContext();
                    String text = "Error Pokemons no encontrados";
                    int duration = Toast.LENGTH_SHORT;
                    toast.show();
                }}

            @Override
            public void onFailure(Call<List<Basepokemon>> call, Throwable t) {
                Toast toast = Toast.makeText(PokedexActivity.this,"ERROR DE CONEXIÓN, no se ha podido realizar la petición.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

    public void exitClick(View v){
        Intent intent = new Intent(this, ProfileActivity.class);
        this.startActivity(intent);
    }
}