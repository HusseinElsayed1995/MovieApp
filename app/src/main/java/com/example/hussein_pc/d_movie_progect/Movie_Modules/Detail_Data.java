package com.example.hussein_pc.d_movie_progect.Movie_Modules;

/**
 * Created by Hussien_pc on 10/1/2016.
 */
public class Detail_Data {
    String name;
    String link;
    String date;
    String rate;
    String id;
    String overview;

                                 ////costructor/////
    public Detail_Data(String name, String link, String date, String rate, String id, String overview) {
        this.name = name;
        this.link = link;
        this.date=  date;
        this.rate=rate;
        this.id=id;
        this.overview = overview;


    }
                                /////Setter&&Getter////////


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }
    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }





    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
}
