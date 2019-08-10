package com.example.harsh.fusionflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.harsh.fusionflix.Model.Constants;
import com.example.harsh.fusionflix.Model.MovieResponse;
import com.example.harsh.fusionflix.Model.Result;
import com.example.harsh.fusionflix.retrofit.ApiService;
import com.example.harsh.fusionflix.retrofit.RetrofitClient;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = MainActivity.class.getSimpleName();
    private MovieAdapter adapter;
    private List<Result> movies;
    private RecyclerView recyclerView;
    Button textTop, textPopular;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textTop = findViewById(R.id.text_pop);
        textPopular = findViewById(R.id.text_popular);

        getPopularMovies();
    }

    private void getTopMovies(){
        ApiService service = RetrofitClient.getRetrofitClient().create(ApiService.class);
        Call<MovieResponse> call = service.getTopMovies(Constants.API_KEY, Constants.LANGUAGE);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                if (movieResponse != null){
                    movies = movieResponse.getResults();
                    initRecyclerView();
                }
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, TAG + "Фатальна помилка, неможливо завантажити дані", Toast.LENGTH_LONG).show();
            }
        });
    }

    private void initRecyclerView(){
        recyclerView = (RecyclerView)findViewById(R.id.recycler);
        adapter = new MovieAdapter(this, movies);
        recyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        adapter.setOnClickListener(new MovieAdapter.OnClickListener() {
            @Override
            public void OnItemClick(int position) {
                Toast.makeText(MainActivity.this, "Clicked at position: " + position, Toast.LENGTH_LONG).show();
                Result result = movies.get(position);
                Intent intent = new Intent(MainActivity.this, MovieActivity.class);
                intent.putExtra(MovieActivity.KEY_MOVIE, result);
                startActivity(intent);
            }
        });
        adapter.notifyDataSetChanged();
    }

    public void onPopularMovies(View view) {
        textPopular.setTextColor(getResources().getColor(R.color.colorAccent));
        textTop.setTextColor(getResources().getColor(android.R.color.white));
        getPopularMovies();
    }

    public void onTopMovies(View view) {
        textTop.setTextColor(getResources().getColor(R.color.colorAccent));
        textPopular.setTextColor(getResources().getColor(android.R.color.white));
        getTopMovies();
    }

    private void getPopularMovies(){
        ApiService service = RetrofitClient.getRetrofitClient().create(ApiService.class);
        Call<MovieResponse>call = service.getPopularMovies(Constants.API_KEY, Constants.LANGUAGE);
        call.enqueue(new Callback<MovieResponse>() {
            @Override
            public void onResponse(Call<MovieResponse> call, Response<MovieResponse> response) {
                MovieResponse movieResponse = response.body();
                movies = movieResponse.getResults();
                initRecyclerView();
            }
            @Override
            public void onFailure(Call<MovieResponse> call, Throwable t) {
                Toast.makeText(MainActivity.this, TAG + "Фатальна помилка, неможливо завантажити дані", Toast.LENGTH_LONG).show();
            }
        });
    }
}
