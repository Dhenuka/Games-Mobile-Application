package com.example.ranga.inclass06_rangam;

import java.util.ArrayList;

/**
 * Created by ranga on 2/20/2017.
 */

public class Details {
    String title;
    String image;
    String overview;
    String genre;
    String pub;
    ArrayList<String> sim;

    @Override
    public String toString() {
        return "Details{" +
                "pub='" + pub + '\'' +
                ", overview='" + overview + '\'' +
                ", genre='" + genre + '\'' +
                ", title='" + title + '\'' +
                '}';
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getPub() {
        return pub;
    }

    public void setPub(String pub) {
        this.pub = pub;
    }

    public ArrayList<String> getSim() {
        return sim;
    }

    public void setSim(ArrayList<String> sim) {
        this.sim = sim;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}
