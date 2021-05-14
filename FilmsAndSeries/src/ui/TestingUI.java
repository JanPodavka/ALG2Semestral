package ui;

import app.WorkWithDTB;
import app.*;


import java.io.IOException;
import java.util.ArrayList;

public class TestingUI {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static void main(String[] args) throws IOException {
        //pokud ještě nemám svoji databázi
        /*String path = "//C:/Users/janpo/Tul_alg/ALG2Semestral/FilmsAndSeries/src/utils/netflix_titles.csv";
        ArrayList<Watching> films = WorkWithDTB.initDTB(path,false); //načtení BR
        Film film1 = new Film("Test Film",true,5,150, films.size()+1, "střílečka");
        assert films != null;
        films.add(film1);
        TVShows tvs = new TVShows("Peaky Blinders",true,10,5,films.size()+1,"Epiiic");
        films.add(tvs);
        WorkWithDTB.saveToMyDTB(films);*/

        //pokud již mám svoji databázi
        ArrayList<Watching> films = WorkWithDTB.initDTB("//C:/Users/janpo/Tul_alg/ALG2Semestral/FilmsAndSeries/src/utils/MyDTB.csv",true);
        System.out.println(films);
    }
}
