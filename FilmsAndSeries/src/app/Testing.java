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

            if(Objects.equals(category,"Movie")){ //if it is a movie
                add = new Film(title,false,0,duration,index,genre);
            }
            else{ //if it is a TV show
                add = new TVShows(title,false,0,duration, index, genre);
            }
            index++;
            films.add(add);
        }
        System.out.println("Test objektu");
        System.out.println(films); //films = všechny vytvořené films z netflixu
//Přídání do arraylistu
        Film film1 = new Film("Test Film",true,5,150,films.size()+1,"střílečka,coool");
        films.add(film1);
        TVShows tvs = new TVShows("Peaky Blinders",true,10,5, films.size()+1, "Epiiic");
        films.add(tvs);
//vytvoření souboru

        BufferedWriter bw = new BufferedWriter(
                new FileWriter("//C:/Users/janpo/Tul_alg/ALG2Semestral/FilmsAndSeries/src/utils/output.csv"));
                bw.write("index;name;watched;rating;duration;genre\n");
       for (Watching film:films) {
            String isMovie = (film instanceof Film?"Movie":"TV show"); //Instance of Film or TVshow ?
            bw.write(  film.getIndex() + ";" + isMovie + ";" + film.getName() + ";" + film.getWatched()+ ";" +
                    ";" + film.getRating()+ ";" + film.getDuration() + ";"+ film.getGenre() +"\n");

      }
       bw.close();



// zápis souboru




    }
}
