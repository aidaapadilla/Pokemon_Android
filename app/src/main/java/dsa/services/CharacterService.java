package dsa.services;
import java.util.List;

import dsa.models.Character;
import dsa.models.Map;
import dsa.models.Pokemon;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface CharacterService {
    @GET("/character/{userId}")
    Call<Character> getCharacter(@Path("userId") String userId);
}
