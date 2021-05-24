package ui;

import app.WorkWithDTB;

import java.io.IOException;
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
        do {
            choice = UILogic.login();
            UILogic.loginMenu(choice);
        }while(choice != 3);
        System.out.println("\n Konec programu");

    }
}
