package com.example.hussein_pc.d_movie_progect.Movie_Modules;

/**
 * Created by Dody-PC on 22-Oct-16.
 */
public class Review_Data {
    String Author_name;
    String content;
                       //constructor
    public Review_Data(String author_name, String content){
        this.Author_name = author_name;
        this.content = content;

    }
                         //Setter and Getter
    public String getAuthor_name() {
        return Author_name;
    }

    public void setAuthor_name(String author_name) {
        Author_name = author_name;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }






}
