package app;


import java.io.*;
import java.util.ArrayList;
import java.util.Objects;


public class Testing {


    /*
        doc
        @Jan Podávka
        https://github.com/JanPodavka
        */
    public static void main(String[] args) throws IOException {
//čtení z netflix databaze -init database
        String path = "//C:/Users/janpo/Tul_alg/ALG2Semestral/FilmsAndSeries/src/utils/netflix_titles.csv";
        File netflixDTB = new File(path);
        BufferedReader nfxDTB = new BufferedReader(new FileReader(netflixDTB));
        System.out.println(nfxDTB.readLine() +"\n");
        //show_id	type	title	date_added	release_year	duration	listed_in


        //
        ArrayList<Watching> films = new ArrayList<>();
//Úvodní zápis a tvorba objektů - 2. metoda
        String line;
        int index = 1;
        String category;
        String title;
        int duration;
        String genre;
        Watching add;
        while ((line = nfxDTB.readLine()) != null){
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
        System.out.println("Test objektu");
        System.out.println(films); //films = všechny vytvořené films z netflixu


//vytvoření souboru


// zápis souboru




    }
}
