package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;

import dsa.models.Basepokemon;
import dsa.models.Map;
import dsa.services.MapService;
import dsa.services.PokemonService;
import dsa.services.UserService;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MapsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private AdapterMaps mAdapter;
    private RecyclerView.LayoutManager layoutManager;

    MapService API;
    List<Map> mapList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        Intent intent = getIntent();

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterMaps();
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

        API = retrofit.create(MapService.class);
    }

    public void doApiCall(){
        Call<List<Map>> call = API.getMaps();
        call.enqueue(new Callback<List<Map>>() {
            @Override
            public void onResponse(Call<List<Map>> call, Response<List<Map>> response) {
                if(response.code()!=404) {
                    mapList=response.body();
                    mAdapter.setData(mapList);
                }
                else {
                    Toast toast = Toast.makeText(MapsActivity.this,"Map list empty",Toast.LENGTH_SHORT);
                    Context context = getApplicationContext();
                    int duration = Toast.LENGTH_SHORT;
                    toast.show();
                }}

            @Override
            public void onFailure(Call<List<Map>> call, Throwable t) {
                Toast toast = Toast.makeText(MapsActivity.this,"ERROR DE CONEXIÓN, no se ha podido realizar la petición.",Toast.LENGTH_SHORT);
                toast.show();
            }
        });
    }

}