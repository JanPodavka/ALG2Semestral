package app;

import java.util.ArrayList;
import java.util.Comparator;

public class Sorting {

    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

    /**
     * @param dtb array list of actual films and serials
     * @return only already wathced films
     */
    public static ArrayList<Watching> filterWatched(ArrayList<Watching> dtb) {
        ArrayList<Watching> films = new ArrayList<>();
        for (Watching film : dtb) {
            if (Boolean.parseBoolean(film.getWatched())) {
                films.add(film);
            }
        }
        return films;
    }

    /**
     * @param dtb arrayList of our DTB
     * @return DTB sorted by rating
     */
    public static ArrayList<Watching> sortByRating(ArrayList<Watching> dtb) {

        ArrayList<Watching> sortedDTB = new ArrayList<>(dtb);
        sortedDTB.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getRating())));

        return sortedDTB;
    }
}
