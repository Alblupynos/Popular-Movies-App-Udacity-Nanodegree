package com.udacity.popularmovies.utils;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ImageView;
import android.widget.TextView;

import com.udacity.popularmovies.R;

public class DetailActivity extends AppCompatActivity {

    private TextView mOriginalTitle;
    private ImageView mMoviePosterThumbnail;
    private TextView mRating;
    private TextView mReleaseData;
    private TextView mOverview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        mOriginalTitle = findViewById(R.id.tv_original_title);
        mMoviePosterThumbnail = findViewById(R.id.iv_movie_poster_thumbnail);
        mRating = findViewById(R.id.tv_rating);
        mReleaseData = findViewById(R.id.tv_releaseData);
        mOverview = findViewById(R.id.tv_overview);

        Intent intentThatStartedThisActivity = getIntent();

        if (intentThatStartedThisActivity != null) {
            if (intentThatStartedThisActivity.hasExtra(Intent.EXTRA_TEXT)) {
                mOriginalTitle.setText(intentThatStartedThisActivity.getStringExtra(Intent.EXTRA_TEXT));
            }
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_detail, menu);
        return true;
    }
}