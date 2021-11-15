package dsa.services;
import java.util.List;

import dsa.models.Object;
import dsa.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;


public interface ObjectService {
    @GET("/objects")
    Call<Object> getObjects(@Body List<Object> objectList );

}
