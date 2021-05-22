package app;

public class Seasons {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
//data

private int rating;
private boolean watched;
private int episodes;

//constructors

    public Seasons(){
    }

    public Seasons(int episodes ,int rating, boolean watched) {
        this.rating = rating;
        this.watched = watched;
        this.episodes = episodes;
    }

    //geters and setters
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    @Override
    public String toString() {
        return "Seasons{" +
                "rating=" + rating +
                ", watched=" + watched +
                ", episodes=" + episodes +
                '}';
    }
}