package app;


import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;


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
        System.out.println(nfxDTB.readLine() +"\n" + nfxDTB.readLine());
        //show_id	type	title	date_added	release_year	duration	listed_in


        //
        ArrayList<Film> films = new ArrayList<>();
//Úvodní zápis a tvorba objektů - 2. metoda
        String line;
        int index = 1;
        String category;
        String title;
        String duration;
        String genre;
        Film add;
        while ((line = nfxDTB.readLine()) != null){
            String[] parts = line.split(";");
            category = parts[1];
            title = parts[2];
            duration = parts[5];
            genre = parts[6];
            //System.out.println(index + " " + category + " " + title + " " + duration + " " + genre);
            index++;
            add = new Film(title,false,0,duration,category,index,genre);
            films.add(add);
        }
        System.out.println("Test objektu");
        System.out.println(films); //films = všechny vytvořené films z netflixu




    }
}
