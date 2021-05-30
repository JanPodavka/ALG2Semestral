package app;

public interface Watching {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

    String getName();

    String getWatched();

    void setWatched(boolean watched);

    String getRating();

    void setRating(int rating);

    String getDuration();

    void setDuration(int duration);

    String getIndex();

    String getGenre();

}
