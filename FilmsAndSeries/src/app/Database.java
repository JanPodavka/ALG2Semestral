package app;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.nio.file.Paths;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Database {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

//data
    private List<Watching> dtb;
//Constructor
    public Database() {

    }

    public Database(List<Watching> dtb){
        this.dtb = dtb;
    }

    /**
     * @return database as List
     */
//non-static methods
    public List<Watching> getDtb() {
        return dtb;
    }

    /**
     * Make user own DTB
     *
     * @param name   ActiveUsername
     * @throws IOException error
     */
    public void saveToMyDTB(String name) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(getActualPath() + "/FilmsAndSeries/src/utils/" + name + "DTB.csv"));
        bw.write("index;type;name;watched;rating;duration;genre\n");
        for (Watching film : dtb) {
            String isMovie = (film instanceof Film ? "Movie" : "TV show"); //Instance of Film or TVshow ?
            bw.write(film.getIndex() + ";" + isMovie + ";" + film.getName() + ";" + film.getWatched() + ";"
                    + film.getRating() + ";" + film.getDuration() + ";" + film.getGenre() + "\n");
        }
        bw.close();

    }

    /**
     * If UserDTB isnt exist, create it with admin account
     *
     * @throws IOException if file cannot be opened
     */
    public void initDTBs() throws IOException {
        String path = getActualPath() + "/FilmsAndSeries/src/utils/UsersDTB.csv";
        File file = new File(path);
        if (!file.exists()) {
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("root;XxZQ6AIDGBTs8z2vrLdbCw=="); //16b encrypited word admin
            bw.close();
        }

    }

    /**
     * @return only watched shows
     */
    public List<Watching> filterWatched() {
        List<Watching> films = new ArrayList<>();
        for (Watching film : dtb) {
            if (Boolean.parseBoolean(film.getWatched())) {
                films.add(film);
            }
        }
        return films;
    }

    /**
     * @return DTB sorted by rating
     */
    public List<Watching> sortByRating() {

        List<Watching> sortedDTB = new ArrayList<>(dtb);
        sortedDTB.sort(Comparator.comparingInt(o -> Integer.parseInt(o.getRating())));

        return sortedDTB;
    }

    /**
     * @return size of our database
     */
    public int getSize(){
        return getDtb().size();
    }

    /**
     * @param watching add to our database
     */
    public void add(Watching watching){
        dtb.add(watching);
    }


//static methods
    /**
     * @param path - path to init DTB
     * @return Read file from given path
     * ! netDTB must be in this format ! :
     * show_id	type	title	date_added	release_year	duration	listed_in
     * delimiter is ;
     */
    public static Database loadDTB(String path, boolean ownDTB) throws NumberFormatException, IOException   {
        //buffer init
        List<Watching> films;//
        File dtb = new File(path);
        try (BufferedReader br = new BufferedReader(new FileReader(dtb))) {
            films = new ArrayList<>();
            //variable init g
            String line;
            int index = 1;
            String category;
            String title;
            int duration;
            String genre;
            boolean watched = false;
            int rating = 0;
            Watching add;
            br.readLine(); //skip the first line
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(";");
                category = parts[1];
                title = parts[2];
                genre = parts[6];
                if (!ownDTB) { //if it is netflix DTB
                    duration = Integer.parseInt(parts[5].split(" ")[0]);
                } else { //if myDTB already exist ------------Mabey split in the methods ?
                    watched = Boolean.parseBoolean(parts[3]);
                    rating = Integer.parseInt(parts[4]);
                    duration = Integer.parseInt(parts[5]);
                }
                add = filmsOrShows(category, title, watched, rating, duration, index, genre);
                index++;
                films.add(add);
            }
        }
        return new Database(films);
    }

    /**
     * @return actual project path
     */
    public static String getActualPath() {
        return Paths.get("").toAbsolutePath().toString();
    }

    /**
     * Create a new instance of a film or Movie
     *
     * @param category c
     * @param title    t
     * @param watched  w
     * @param rating   r
     * @param duration d
     * @param index    i
     * @param genre    g
     * @return created instance
     */
    public static Watching filmsOrShows(String category, String title, boolean watched, int rating, int duration, int index, String genre) {
        if (Objects.equals(category, "Movie")) { //if it is a movie
            return new Film(title, watched, rating, duration, index, genre);
        } else { //if it is a TV show
            return new TVShows(title, watched, rating, duration, index, genre);
        }
    }

}
