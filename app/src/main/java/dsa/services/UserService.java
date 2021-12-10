package dsa.services;

import dsa.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;

public interface UserService{
    @POST("/user")
    Call<String> register(@Body User user); //Register??
    @POST("/login")
    Call<User> login(@Body User user);  //nom i usuari?
}