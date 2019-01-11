package com.udacity.popularmovies;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.udacity.popularmovies.model.Movie;
import com.udacity.popularmovies.utils.DetailActivity;
import com.udacity.popularmovies.utils.JsonUtils;
import com.udacity.popularmovies.utils.NetworkUtils;

import java.net.URL;
import java.util.List;

import static com.udacity.popularmovies.utils.NetworkUtils.SORT_POPULAR;
import static com.udacity.popularmovies.utils.NetworkUtils.SORT_TOP_RATED;

public class MainActivity extends AppCompatActivity implements MovieAdapter.MovieAdapterOnClickHandler {

    private RecyclerView mRecyclerView;
    private MovieAdapter mMovieAdapter;

    private TextView mErrorMessageDisplay;

    private ProgressBar mLoadingIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mRecyclerView = findViewById(R.id.recyclerview_movie);
        mErrorMessageDisplay = findViewById(R.id.tv_error_message_display);

        GridLayoutManager layoutManager
                = new GridLayoutManager(this, 2, LinearLayoutManager.VERTICAL, false);

        mRecyclerView.setLayoutManager(layoutManager);

        mMovieAdapter = new MovieAdapter(this);

        mRecyclerView.setAdapter(mMovieAdapter);

        mLoadingIndicator = findViewById(R.id.pb_loading_indicator);

        loadMovieData(SORT_POPULAR);
    }

    private void loadMovieData(String sortType) {
        showMovieView();
        new FetchMovieTask().execute(sortType);
    }

    @Override
    public void onClick(Movie movie) {
        Context context = this;
//        Toast.makeText(context, movie.getOriginalTitle(), Toast.LENGTH_SHORT)
//                .show();
        Intent intentToStartDetailActivity = new Intent(context, DetailActivity.class);
        intentToStartDetailActivity.putExtra(Intent.EXTRA_TEXT, movie.getOriginalTitle());
        startActivity(intentToStartDetailActivity);
    }

    private void showMovieView() {
        mErrorMessageDisplay.setVisibility(View.INVISIBLE);
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void showErrorMessage() {
        mRecyclerView.setVisibility(View.INVISIBLE);
        mErrorMessageDisplay.setVisibility(View.VISIBLE);
    }

    public class FetchMovieTask extends AsyncTask<String, Void, List<Movie>> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mLoadingIndicator.setVisibility(View.VISIBLE);
        }

        @Override
        protected List<Movie> doInBackground(String... params) {

            URL movieRequestUrl = NetworkUtils.buildUrl(params[0]);

            try {
                String jsonMovieResponse = NetworkUtils
                        .getResponseFromHttpUrl(movieRequestUrl);

                List<Movie> simpleJsonMovieData = JsonUtils.parseMovieJson(jsonMovieResponse);

                return simpleJsonMovieData;

            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<Movie> movieData) {
            mLoadingIndicator.setVisibility(View.INVISIBLE);
            if (movieData != null) {
                showMovieView();
                mMovieAdapter.setMovieData(movieData);
            } else {
                showErrorMessage();
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        mMovieAdapter.setMovieData(null);
        if (id == R.id.menu_most_popular) {
            loadMovieData(SORT_POPULAR);
        } else if (id == R.id.menu_top_rated) {
            loadMovieData(SORT_TOP_RATED);
        }
        item.setChecked(true);
        return true;
    }
}