package app;

import java.io.*;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Objects;

public class WorkWithDTB {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

    /**
     * @return actual project path
     */
    public static String getActualPath(){
        return Paths.get("").toAbsolutePath().toString();
    }

    /**
     * @param path - path to init DTB
     * @return Read file from given path
     * ! netDTB must be in this format ! :
     * show_id	type	title	date_added	release_year	duration	listed_in
     * delimiter is ;
     */
    public static ArrayList<Watching> loadDTB(String path, boolean ownDTB) {
        //buffer init
        try {
            File dtb = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(dtb));
            ArrayList<Watching> films = new ArrayList<>();
            //variable init g
            String line;
            int index = 1;
            String category;String title;int duration;String genre;
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
                add = filmsOrShows(category,title,watched,rating,duration,index,genre);
                index++;
                films.add(add);
            }
            br.close();
            return films;
        } catch (IOException e) {
            e.printStackTrace();
            return null; //nothing to return in bad path
        }

    }

    public static void saveToMyDTB(ArrayList<Watching> seznam,String name) throws IOException {
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(getActualPath() + "/FilmsAndSeries/src/utils/" + name + "DTB.csv",true));
        bw.write("index;type;name;watched;rating;duration;genre\n");
        for (Watching film : seznam) {
            String isMovie = (film instanceof Film ? "Movie" : "TV show"); //Instance of Film or TVshow ?
            bw.write(film.getIndex() + ";" + isMovie + ";" + film.getName() + ";" + film.getWatched() + ";"
                    + film.getRating() + ";" + film.getDuration() + ";" + film.getGenre() + "\n");
        }
        bw.close();

    }

    /**If UserDTB isnt exist, create it with admin account
     * @throws IOException if file cannot be opened
     */
    public static void initDTBs() throws IOException {
        String path = getActualPath() + "/FilmsAndSeries/src/utils/UsersDTB.csv";
        File file = new File(path);
        if(!file.exists()){
            BufferedWriter bw = new BufferedWriter(new FileWriter(file));
            bw.write("root;admin");
            bw.close();
        }

    }

    public static Watching filmsOrShows(String category, String title, boolean watched, int rating, int duration, int index, String genre){
        if (Objects.equals(category, "Movie")) { //if it is a movie
            return new Film(title, watched, rating, duration, index, genre);
        } else { //if it is a TV show
            return new TVShows(title, watched, rating, duration, index, genre);
        }
    }



}
