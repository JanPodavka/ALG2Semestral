package app;

public class seasons {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
//data
private Episode[] episodes;
private int rating;
private boolean watched;

//constructors

    public seasons(){
    }

    public seasons(Episode[] episodes, int rating, boolean watched) {
        this.episodes = episodes;
        this.rating = rating;
        this.watched = watched;
    }

    //geters and setters
    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }
}