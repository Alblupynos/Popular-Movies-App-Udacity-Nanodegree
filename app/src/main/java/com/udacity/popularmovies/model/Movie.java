package com.udacity.popularmovies.model;

public class Movie {

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

}
