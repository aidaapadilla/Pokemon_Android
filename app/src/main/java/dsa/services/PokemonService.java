package dsa.services;

import java.util.List;

import dsa.models.Pokemon;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;


public interface PokemonService {
    @GET("/pokemons")
    Call<List<Pokemon>> getPokemons();
    //Els gets diria que no poden portar body
}
