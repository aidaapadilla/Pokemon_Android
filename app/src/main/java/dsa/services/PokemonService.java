package dsa.services;

import java.util.List;

import dsa.models.Pokemon;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;


public interface PokemonService {
    @GET("/pokemons")
    Call<Pokemon> getPokemons(@Body List<Pokemon> pokemonsList);
}
