package ui;

import app.User;
import app.Watching;
import app.WorkWithDTB;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.ArrayList;
import java.util.Scanner;

public class UILogic {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static Scanner sc = new Scanner(System.in);


    //text void methods
    public static void welcomeScreen() {
        System.out.println("**************************************************************");
        System.out.println("*                  Databáze Seriálů a filmů                  *");
        System.out.println("**************************************************************");
        System.out.println("*                                         "+ actualTime() +" ");
        System.out.println("*  1. Registace                                              *");
        System.out.println("*  2. Přihlášení                                             *");
        System.out.println("*  3. Ukončit program                                        *");
        System.out.println("**************************************************************");
        System.out.println("Zadejte volbu: ");
    }

    public static void afterLoginMenu(String name) {
        System.out.println("**************************************************************");
        System.out.println("                      " + "Vítej " + name + " !");
        System.out.println("**************************************************************");
        System.out.println("*                                         "+ actualTime() +" ");
        System.out.println("*                                                            *");
        System.out.println("*                       1. Přidat film/seriál                *");
        System.out.println("*                       2. Upravit                           *");
        System.out.println("*                       3. Ukončit program                   *");
        System.out.println("**************************************************************");
        System.out.println("Zadejte volbu: ");
    }

    public static Watching addShowMenu(ArrayList<Watching> dtb) {
        System.out.println("Movie nebo serial ? ");
        String category = sc.next();
        System.out.println("Zadejte název seriálu/filmu: ");
        sc.nextLine();
        String title = sc.nextLine();
        System.out.println("Již zhlédnuto ? 1-ano 0-ne ");
        int watched = sc.nextInt();
        System.out.println("Délka (v min/seriích)");
        int duration = sc.nextInt();
        System.out.println("hodnocení od 1-10");
        int rating = sc.nextInt();
        System.out.println("Žanry");
        sc.nextLine();
        String genre = sc.nextLine();
        assert dtb != null;
        return WorkWithDTB.filmsOrShows(category, title, (watched == 1), rating, duration, dtb.size() + 1, genre);
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
            case 1, 2 -> {
                System.out.println("Zadej jméno");
                name = sc.next();
                System.out.println("Zadej heslo");
                password = sc.next();
                return User.register(name, password, choice);
            }
            default -> {
                return null;
            }
        }
    }

    public static StringBuilder actualTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        StringBuilder myString = new StringBuilder();
        return myString.append(dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)));
    }


}
