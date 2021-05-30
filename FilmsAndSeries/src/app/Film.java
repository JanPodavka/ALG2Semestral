package app;

public class Film implements Watching {
    /*
   doc
   @Jan Podávka
   https://github.com/JanPodavka
   */
//Data
    private String title;
    private boolean watched;
    private int rating;
    private int duration;
    private int index;
    private String genre;
//constructors

    public Film() {
    }

    public Film(String title, boolean watched, int rating, int duration, int index, String genre) {
        this.title = title;
        this.watched = watched;
        this.rating = rating;
        this.duration = duration;
        this.index = index;
        this.genre = genre;
    }
//getters and setters

    /**
     * @return name
     */
    @Override
    public String getName() {
        return title;
    }

    /**
     * @return watched
     */
    @Override
    public String getWatched() {
        return String.valueOf(watched);
    }

    /**
     * @param watched w
     */
    @Override
    public void setWatched(boolean watched) {
        this.watched = watched;
    }

    /**
     * @return rating
     */
    @Override
    public String getRating() {
        return String.valueOf(rating);
    }

    /**
     * @param rating r
     */
    @Override
    public void setRating(int rating) {
        this.rating = rating;
    }

    /**
     * @return duration
     */
    @Override
    public String getDuration() {
        return String.valueOf(duration);
    }

    /**
     * @param duration d
     */
    @Override
    public void setDuration(int duration) {
        this.duration = duration;
    }

    /**
     * @return index
     */
    @Override
    public String getIndex() {
        return String.valueOf(index);
    }

    /**
     * @return genre
     */
    @Override
    public String getGenre() {
        return genre;
    }

    /**
     * @return String of our object info
     */
    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", watched=" + watched +
                ", rating=" + rating +
                ", duration='" + duration + "min" + '\'' +
                ", index=" + index +
                ", genre=" + genre + "\n \n";
    }
}
