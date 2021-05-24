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
        ArrayList<Watching> dtb;
        User activeUser;
        String path;
        //***********************
        do {
            choice = UILogic.login();
            activeUser = UILogic.getActiveUser(choice);
            if (choice == 1 && activeUser != null) {
                dtb = WorkWithDTB.loadDTB(WorkWithDTB.getActualPath() +
                        "/FilmsAndSeries/src/utils/netflix_titles.csv", false);
                assert dtb != null;
                path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/"
                        + activeUser.getName() + "DTB.csv";
                WorkWithDTB.saveToMyDTB(dtb, activeUser.getName());
                break;
            } else if (choice == 2 && activeUser != null) { // login - pouze načíst již vytvořenou databázi (dle jména)
                dtb = WorkWithDTB.loadDTB(WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/"
                        + activeUser.getName() + "DTB.csv", true);
                path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/"
                        + activeUser.getName() + "DTB.csv";
                break;
            } else if (choice == 3) {
                return;
            }
        } while (activeUser == null);
        //*********************

        while ((choice = sc.nextInt()) != 3) {
            UILogic.afterLoginMenu(activeUser.getName()); // Next text menu
            System.out.println(choice);
        }
    }
}

