package dsa.services;

import java.util.List;
import dsa.models.Ranking;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;

public interface RankingService {
        @GET("/ranking")
        Call<Ranking> getRanking(@Body List<Ranking> rankingList);
    }
