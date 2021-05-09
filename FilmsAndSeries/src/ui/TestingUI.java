package ui;

import app.WorkWithDTB;
import app.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.nio.file.Path;
import java.security.spec.RSAOtherPrimeInfo;
import java.util.ArrayList;

public class TestingUI {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static void main(String[] args) throws IOException {
        String path = "//C:/Users/janpo/Tul_alg/ALG2Semestral/FilmsAndSeries/src/utils/netflix_titles.csv";
        ArrayList<Watching> films = WorkWithDTB.initDTB(path); //načtení BR
        System.out.println(films);


    }
}
