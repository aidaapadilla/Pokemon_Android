package dsa.ejercicios_practica.pokemon_android;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.util.List;
import java.util.logging.Logger;

import dsa.models.Object;
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

    static final String BASE_URL = "http://10.0.2.2:8080/dsaApp/";
    ObjectService API;
    List<Object>objectList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_objects);

        recyclerView = (RecyclerView) findViewById(R.id.my_recycler_view);
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
        mAdapter = new AdapterObject();
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

        API = retrofit.create(ObjectService.class);
    }

    //Devuelve la lista con todos los objetos
    public void doApiCall(){
        Call<List<Object>> call = API.getObjects();
        call.enqueue(new Callback<List<Object>>() {
            @Override
            public void onResponse(Call<List<Object>> call, Response<List<Object>> response) {
                if(!response.body().isEmpty()) {
                    objectList = response.body();
                    mAdapter.setData(objectList);

                    //tracksList.forEach(track -> System.out.println(track.title));
                }
                else {
                    Toast toast = Toast.makeText(dsa.ejercicios_practica.pokemon_android.AllObjectsActivity.this,"Lista de objetos vacía",Toast.LENGTH_SHORT);
                    toast.show();
                }
            }

            @Override
            public void onFailure(Call<List<Object>> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }
}