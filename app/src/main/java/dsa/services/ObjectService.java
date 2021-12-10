package dsa.services;
import java.util.List;

import dsa.models.Objects;
import retrofit2.Call;
import retrofit2.http.POST;


public interface ObjectService {
    @POST("/objects")
    Call<List<Objects>> getObjects();


}
