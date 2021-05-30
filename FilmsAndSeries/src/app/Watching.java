package app;

public interface Watching{
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

    String getName();
    String getWatched();
    String getRating();
    String getDuration();
    String getIndex();
    String getGenre();

    void setWatched(boolean watched);
    void setRating(int rating);
    void setDuration(int duration);

}
