package dsa.services;

import java.util.List;

import dsa.models.Character;
import dsa.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface CharacterService {
    final String BASE_URL = "http://147.83.7.204:8080";
    @GET("/dsaApp/endpoint/character/{name}")
    Call<Character> getCharacter(@Path("name") String name);
    @POST("/dsaApp/endpoint/character")
    Call<Character> newCharacter(@Body Character character);
    @GET("/dsaApp/endpoint/ranking")
    Call<List<Character>> getRanking();
    @PUT("/dsaApp/endpoint/updatecharacter")
    Call<Character> updateCharacter(@Body Character character);
}
