package dsa.services;

import java.util.List;

import dsa.models.Pokemons;
import retrofit2.Call;
import retrofit2.http.GET;


public interface PokemonService {
    @GET("/pokemons")
    Call<List<Pokemons>> getPokemons();
    //Els gets diria que no poden portar body
}
