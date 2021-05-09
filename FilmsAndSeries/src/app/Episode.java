package app;

public class Episode {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

//data
    private int duration;

//constructors
    public Episode(){
        duration = 0;
    }
    public Episode(int duration){
        this.duration = duration;
    }
//getters and Setters
    public int getDuration() {
        return duration;
    }
    public void setDuration(int duration) {
        this.duration = duration;
    }
}
