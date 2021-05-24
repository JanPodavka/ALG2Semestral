package ui;

import app.User;

import java.io.IOException;
import java.util.Scanner;

public class UILogic {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static Scanner sc = new Scanner(System.in);


//text void methods
    public static void welcomeScreen(){
        System.out.println("**************************************************************");
        System.out.println("*                  Databáze Seriálů a filmů                  *");
        System.out.println("*  1. Registace                                              *");
        System.out.println("*  2. Přihlášení                                             *");
        System.out.println("*  3. Ukončit program                                        *");
        System.out.println("**************************************************************");
        System.out.println("Zadejte volbu: ");
    }
    public static void afterLoginMenu(String name){
        System.out.println("**************************************************************");
        System.out.println("                      "+ "Vítej "+ name + " !"                 );
        System.out.println("**************************************************************");
        System.out.println("*                                                            *");
        System.out.println("*                       1. Přidat film/seriál                *");
        System.out.println("*                       2. Upravit                           *");
        System.out.println("*                       3. Ukončit program                   *");
        System.out.println("**************************************************************");
        System.out.println("Zadejte volbu: ");
    }

//logic methods
    /**
     * @return a = 1 for registration
     * 2 for login
     * 3 for exit
     */
    public static int login() {
        welcomeScreen();
        int a;

        while ((a = sc.nextInt()) != 3) {
            if (a == 1) {
                return a;
            } else if (a == 2) {
                return a;
            } else {
                System.out.println("Zadejte validní možnost !");
            }
        }
        return 3;
    }
    public static User getActiveUser(int choice) throws IOException {
        String name;
        String password;
        switch (choice) {
            case 1,2 -> {
                System.out.println("Zadej jméno");
                name = sc.next();
                System.out.println("Zadej heslo");
                password = sc.next();
                return User.register(name,password,choice);
            }
            default-> {
                return null;
            }
        }
    }
    public static int loadChoice(){
        return sc.nextInt();
    }


}
