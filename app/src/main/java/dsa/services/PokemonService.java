package dsa.services;

import java.util.List;

import dsa.models.Basepokemon;
import dsa.models.Pokemons;
import retrofit2.Call;
import retrofit2.http.GET;


public interface PokemonService {
    @GET("/dsaApp/endpoint/pokemons")
    Call<List<Basepokemon>> getPokemons();
    //Els gets diria que no poden portar body
}
