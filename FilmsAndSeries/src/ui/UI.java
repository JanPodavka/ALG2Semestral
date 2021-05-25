package ui;

import app.User;
import app.Watching;
import app.WorkWithDTB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class UI {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException {
        WorkWithDTB.initDTBs(); //check if user file already existed,if not create admin accountS
        int choice;
        ArrayList<Watching> dtb = null;
        User activeUser;
        String path;
        //***********************
        do {
            choice = UILogic.login();
            activeUser = UILogic.getActiveUser(choice);
            if (choice == 1 && activeUser != null) {
                dtb = WorkWithDTB.loadDTB(WorkWithDTB.getActualPath() +
                        "/FilmsAndSeries/src/utils/netflix_titles.csv", false);
                path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/"
                        + activeUser.getName() + "DTB.csv";
                assert dtb != null;
                WorkWithDTB.saveToMyDTB(dtb, activeUser.getName());
                break;
            } else if (choice == 2 && activeUser != null) { // login - pouze načíst již vytvořenou databázi (dle jména)
                path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/"
                        + activeUser.getName() + "DTB.csv";
                dtb = WorkWithDTB.loadDTB(path, true);
                break;
            } else if (choice == 3) {
                return;
            }
        } while (activeUser == null);
        //*********************
        choice = 0;
        do {
            UILogic.afterLoginMenu(activeUser.getName()); // Next text menu
            if (choice == 1) {
                assert dtb != null;
                dtb.add(UILogic.addShowMenu(dtb)); //add film/tv show to dtb
            } else if (choice == 2) {

            }

        } while ((choice = sc.nextInt()) != 3);
    }
}

