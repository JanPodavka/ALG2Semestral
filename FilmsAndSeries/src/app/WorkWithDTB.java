package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class WorkWithDTB {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */


    /**
     * @param path - path to init DTB
     * @return
     * Read file from given path
     * ! DTB must be in this format ! :
     * show_id	type	title	date_added	release_year	duration	listed_in
     * delimiter is ;
     * @throws IOException - To...do
     */
    public static ArrayList<Watching> initDTB(String path) throws IOException {
        //buffer init
        File netflixDTB = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(netflixDTB));
        ArrayList<Watching> films = new ArrayList<>();
        //variable init
        String line;
        int index = 1;
        String category;
        String title;
        int duration;
        String genre;
        Watching add;
        br.readLine(); //skip the first line
        //Objects making
        while ((line = br.readLine()) != null){
            String[] parts = line.split(";");
            category = parts[1];
            title = parts[2];
            duration = Integer.parseInt(parts[5].split(" ")[0]);
            genre = parts[6];
            index++;
            if(Objects.equals(category,"Movie")){ //if it is a movie
                add = new Film(title,false,0,duration,index,genre);
            }
            else{ //if it is a TV show
                add = new TVShows(title,false,0,duration, index, genre);
            }
            films.add(add);
        }
        return films;
    }


    public static void main(String[] args) {

    }

}
