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

    public Film(){
    }

    public Film(String title, boolean watched, int rating, int duration,int index,String genre) {
        this.title = title;
        this.watched = watched;
        this.rating = rating;
        this.duration = duration;
        this.index = index;
        this.genre = genre;
    }
//getters and setters

    @Override
    public String getName() {
        return title;
    }

    @Override
    public String getWatched() {
        return String.valueOf(watched);
    }

    @Override
    public String getRating() {
        return String.valueOf(rating);
    }

    @Override
    public String getDuration() {
        return String.valueOf(duration);
    }

    @Override
    public String getIndex() {
        return String.valueOf(index);
    }

    @Override
    public String getGenre() {
        return genre;
    }

    @Override
    public String toString() {
        return "Film{" +
                "title='" + title + '\'' +
                ", watched=" + watched +
                ", rating=" + rating +
                ", duration='" + duration+"min" + '\'' +
                ", index=" + index +
                ", genre=" + genre +"\n \n";
    }
}
