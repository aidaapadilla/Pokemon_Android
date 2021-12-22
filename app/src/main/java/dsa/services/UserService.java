package dsa.services;

import dsa.models.Credentials;
import dsa.models.User;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;

public interface UserService{
    final String BASE_URL = "http://147.83.7.204:8080";
    @POST("/dsaApp/endpoint/user")
    Call<User> register(@Body User user); //Register??
    @POST("/dsaApp/endpoint/login")
    Call<User> login(@Body Credentials credentials);
    @PUT("/dsaApp/endpoint/updateuser")
    Call<User> update(@Body User user);
    @PUT("/dsaApp/endpoint/delete/{username}")
    Call<User> deleteUser(@Path("username") String name);
}