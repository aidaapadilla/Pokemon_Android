package dsa.services;

import dsa.models.Character;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;

public interface CharacterService {
    final String BASE_URL = "http://10.0.2.2:8080";
    @GET("/character/{name}")
    Call<Character> getCharacter(@Path("name") String name);
    @POST("/character")
    Call<Character> newCharacter(@Body Character character);
}
