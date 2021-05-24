package ui;

import app.User;
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

       int choice = UILogic.Login();
        String name;
        String password;
        switch (choice) {
            case 1 -> {
                //funkce pro načtení uživatelovi databáze
                System.out.println("Zadej jméno");
                name = sc.next();
                System.out.println("Zadej heslo");
                password = sc.next();
                User activeUser = User.register(name,password);
                if(activeUser != null){
                    System.out.println("Vítej " + activeUser.getName() + " !");
                }
                else {
                    System.out.println("Uživatelské jméno je již zabrané !");
                }
            }
            case 2 -> {
                //funkce pro načtení ntf databaze + založení vlastní dtb
                System.out.println("Zadej jméno");
                name = sc.next();
                System.out.println("Zadej heslo");
                password = sc.next();
            }
            case 3 -> {
                System.out.println("program ukončen");
                return;
            }
        }
        System.out.println("\n Konec programu");

    }
}
