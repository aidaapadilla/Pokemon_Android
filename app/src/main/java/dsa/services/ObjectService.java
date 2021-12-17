package dsa.services;
import java.util.List;

import dsa.models.Character;
import dsa.models.ObjectWithCharacter;
import dsa.models.Objects;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


public interface ObjectService {
    final String BASE_URL = "http://10.0.2.2:8080";
    @GET("/dsaApp/endpoint/Store/ShowProducts")
    Call<List<Objects>> getObjects();
    @POST("dsaApp/endpoint/Store/Shopping")
    Call<Character> buyObject(@Body ObjectWithCharacter objectWithCharacter);

}
