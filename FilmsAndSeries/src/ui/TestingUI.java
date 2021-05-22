package ui;

import app.WorkWithDTB;
import app.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;

public class TestingUI {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static void main(String[] args) throws IOException {
        //pokud ještě nemám svoji databázi

        String path = "//C:/Users/janpo/Tul_alg/ALG2Semestral/FilmsAndSeries/src/utils/netflix_titles.csv";
        ArrayList<Watching> films = WorkWithDTB.initDTB(path,false); //načtení BR
        assert films != null;
        Film film1 = new Film("Test Film",true,5,150, films.size()+1, "střílečka");
        films.add(film1);
        TVShows tvs = new TVShows("Peaky Blinders",true,10,5,films.size()+1,"Epiiic");
        films.add(tvs);
        WorkWithDTB.saveToMyDTB(films);

       /* //pokud již mám svoji databázi
        ArrayList<Watching> films = WorkWithDTB.initDTB("//C:/Users/janpo/Tul_alg/ALG2Semestral/FilmsAndSeries/src/utils/MyDTB.csv",true);
        System.out.println(films);
        assert films != null;
        System.out.println(films.get(films.size()-1));

        //TEST PŘIDÁNÍ SERIE k serialu
        Seasons s1 = new Seasons(15,8,true);
        Seasons s2 = new Seasons(14,8,true);
        Seasons[] ss = {s1,s2};

        TVShows filmek = (TVShows) films.get((films.size()-1));
        filmek.setSeasons(ss);
        System.out.println(filmek);
        System.out.println(filmek.getSeasons()[1].toString());*/
    }
}
