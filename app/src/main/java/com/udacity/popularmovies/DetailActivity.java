package com.udacity.popularmovies;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;
import com.udacity.popularmovies.model.Movie;
import com.udacity.popularmovies.utils.NetworkUtils;

public class DetailActivity extends AppCompatActivity {

    public static final String PARCELABLE_MOVIE = "parcelable_movie";

    private ImageView mMoviePosterThumbnail;
    private TextView mRating;
    private TextView mReleaseData;
    private TextView mOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mMoviePosterThumbnail = findViewById(R.id.iv_movie_poster_thumbnail);
        mRating = findViewById(R.id.tv_rating);
        mReleaseData = findViewById(R.id.tv_releaseData);
        mOverview = findViewById(R.id.tv_overview);

        Intent intent = getIntent();

        if (intent != null) {
            if (intent.hasExtra(PARCELABLE_MOVIE)) {
                Movie movie = intent.getParcelableExtra(PARCELABLE_MOVIE);
                setTitle(movie.getOriginalTitle());
                Picasso.with(this)
                        .load(NetworkUtils.buildPosterUri(movie.getPoster()))
                        .into(mMoviePosterThumbnail);
                mRating.setText(String.format(getString(R.string.rating_format), movie.getRating()));
                mReleaseData.setText(movie.getReleaseData());
                mOverview.setText(movie.getOverview());
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }
}