package app;

public class TVShows implements Watching {
    private final String title;
    /*
   doc
   @Jan Podávka
   https://github.com/JanPodavka
   */
//data
    private int rating;
    private boolean watched;
    private int duration;
    private int index;
    private String genre;

//constructors

    public TVShows(int rating, String title, boolean watched, int index, String genre) {
        this.rating = rating;
        this.title = title;
        this.watched = watched;
        this.index = index;
        this.genre = genre;
    }

    public TVShows(String title, boolean watched, int rating, int duration, int index, String genre) {
        this.rating = rating;
        this.title = title;
        this.watched = watched;
        this.index = index;
        this.genre = genre;
        this.duration = duration;
    }
    //geters and setters

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getWatched() {
        return String.valueOf(watched);
    }

    @Override
    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    @Override
    public String getRating() {
        return String.valueOf(rating);
    }

    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String getDuration() {
        return String.valueOf(duration);
    }

    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    @Override
    public String getIndex() {
        return String.valueOf(index);
    }

    @Override
    public String getGenre() {
        return genre;
    }

    /**
     * @return String of our object info
     */
    @Override
    public String toString() {
        return "TVShows{" +
                "title='" + title + '\'' +
                ", watched=" + watched +
                ", rating=" + rating +
                ", duration='" + duration + "seasons" + '\'' +
                ", index=" + index +
                ", genre='" + genre + "} \n \n";
    }
}
