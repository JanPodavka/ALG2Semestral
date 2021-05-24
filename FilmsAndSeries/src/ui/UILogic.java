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


    /**
     * @return a = 1 for login
     * 2 for register
     * 3 for exit
     */
    public static int login() {
        System.out.println("Chcete se příhlásit stisknětě 1,pro registraci stiskněte 2, 3 pro exit");
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
    public static User loginMenu(int choice) throws IOException {
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
                return activeUser;
            }
            case 2 -> {
                //funkce pro načtení ntf databaze + založení vlastní dtb
                System.out.println("Zadej jméno");
                name = sc.next();
                System.out.println("Zadej heslo");
                password = sc.next();
                return null;
            }
            case 3 -> {
                System.out.println("program ukončen");
                return null;
            }
        }
        return null;
    }


}
