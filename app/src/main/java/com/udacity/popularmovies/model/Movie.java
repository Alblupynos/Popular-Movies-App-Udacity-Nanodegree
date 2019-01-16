package com.udacity.popularmovies.model;

import android.os.Parcel;
import android.os.Parcelable;

public class Movie implements Parcelable {

    private String originalTitle;
    private String poster;
    private String overview;
    private String rating;
    private String releaseData;

    public Movie(){
    }

    public Movie(String originalTitle, String poster, String overview, String rating, String releaseData){
        this.originalTitle = originalTitle;
        this.poster = poster;
        this.overview = overview;
        this.rating = rating;
        this.releaseData = releaseData;
    }

    protected Movie(Parcel in) {
        originalTitle = in.readString();
        poster = in.readString();
        overview = in.readString();
        rating = in.readString();
        releaseData = in.readString();
    }

    public static final Creator<Movie> CREATOR = new Creator<Movie>() {
        @Override
        public Movie createFromParcel(Parcel in) {
            return new Movie(in);
        }

        @Override
        public Movie[] newArray(int size) {
            return new Movie[size];
        }
    };

    public String getOriginalTitle() { return originalTitle;}
    public void setOriginalTitle(String originalTitle) {this.originalTitle = originalTitle;}

    public String getPoster() { return poster;}
    public void setPoster(String poster) {this.poster = poster;}

    public String getOverview() { return overview;}
    public void setOverview(String overview) {this.overview = overview;}

    public String getRating() { return rating;}
    public void setRating(String rating) {this.rating = rating;}

    public String getReleaseData() { return releaseData;}
    public void setReleaseData(String releaseData) {this.releaseData = releaseData;}

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(originalTitle);
        dest.writeString(poster);
        dest.writeString(overview);
        dest.writeString(rating);
        dest.writeString(releaseData);
    }
}
