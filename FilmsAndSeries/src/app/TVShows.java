package app;

import java.util.Arrays;

public class TVShows implements Watching {
     /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
//data
    private seasons[] seasons;
    private int rating;
    private String title;
    private boolean watched;
    private int duration;
    private int index;
    private String genre;

//constructors

    public TVShows(seasons[] seasons, int rating, String title, boolean watched, int index, String genre) {
        this.seasons = seasons;
        this.rating = rating;
        this.title = title;
        this.watched = watched;
        this.index = index;
        this.genre = genre;
    }
    public TVShows(String title,boolean watched, int rating,int duration, int index, String genre) {
        this.rating = rating;
        this.title = title;
        this.watched = watched;
        this.index = index;
        this.genre = genre;
        this.duration = duration;
    }

    //methods
    @Override
    public String getName() {
        return null;
    }

    @Override
    public String toString() {
        return "TVShows{" +
                "title='" + title + '\'' +
                ", watched=" + watched +
                ", rating=" + rating +
                ", series=" + Arrays.toString(seasons) +
                ", duration='" + duration+"seasons" + '\'' +
                ", index=" + index +
                ", genre='" + genre + "} \n \n";
    }
}
