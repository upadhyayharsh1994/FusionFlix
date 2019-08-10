package com.example.harsh.fusionflix.retrofit;

import com.example.harsh.fusionflix.Model.Constants;
import com.example.harsh.fusionflix.Model.MovieResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET(Constants.URL_POPULAR)
    Call<MovieResponse> getPopularMovies
            (@Query("api_key") String apiKey,
             @Query("language") String language);

    @GET(Constants.URL_TOP)
    Call<MovieResponse> getTopMovies
            (@Query("api_key") String apiKey,
             @Query("language") String language);
}
