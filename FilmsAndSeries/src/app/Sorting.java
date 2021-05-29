package app;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Sorting {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static ArrayList<Watching> filterWatched(ArrayList<Watching> dtb){
        ArrayList<Watching> films = new ArrayList<>();
        for (Watching film:dtb) {
            if(Boolean.parseBoolean(film.getWatched())){
                films.add(film);
            }
        }
        return films;
    }

    public static ArrayList<Watching> sortByRating(ArrayList<Watching> dtb) {

        ArrayList<Watching> sortedDTB = new ArrayList<>(dtb);
        Collections.sort(sortedDTB, Comparator.comparingInt(o -> Integer.parseInt(o.getRating())));

        return sortedDTB;
    }
}
