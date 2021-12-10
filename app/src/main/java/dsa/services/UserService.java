package dsa.services;

import dsa.models.Credentials;
import dsa.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService{
    final String BASE_URL = "http://10.0.2.2:8080";
    @POST("/dsaApp/endpoint/user")
    Call<User> register(@Body User user); //Register??
    @POST("/dsaApp/endpoint/login")
    Call<User> login(@Body Credentials credentials);
}