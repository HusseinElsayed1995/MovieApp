package com.example.hussein_pc.d_movie_progect.Movie_Modules;

/**
 * Created by Dody-PC on 22-Oct-16.
 */
public class Trailer_Data {

    private String key;
    private String name;

    public Trailer_Data(String key, String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
