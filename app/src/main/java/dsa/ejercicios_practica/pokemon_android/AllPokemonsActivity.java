package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dsa.models.Pokemon;
import dsa.services.PokemonService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AllPokemonsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterPokemon mAdapter;
    private RecyclerView.LayoutManager layoutManager;
    PokemonService API;

    static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_pokemons);

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
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .build();

        API = retrofit.create(PokemonService.class);
    }

    public void doApiCall(){
        Call<List<Pokemon>> call = API.getPokemons();
        call.enqueue(new Callback<List<Pokemon>>() {
            @Override
            public void onResponse(Call<List<Pokemon>> call, Response<List<Pokemon>> response) {
                if(response.isSuccessful()) {
                    List<Pokemon> pokemonList = response.body();
                    mAdapter.setData(pokemonList);

                    //tracksList.forEach(track -> System.out.println(track.title));
                }
                else {
                    //System.out.println(response.errorBody());
                }
            }

            @Override
            public void onFailure(Call<List<Pokemon>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }



}