package ui;

import app.User;
import app.Watching;
import app.Database;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Objects;
import java.util.Scanner;

public class UIMethods {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static Scanner sc = new Scanner(System.in);


    /**
     * Welcome menu gui
     */
    //text void methods
    public static void welcomeScreen() {
        System.out.println("**************************************************************");
        System.out.println("*                  Databáze Seriálů a filmů                  *");
        System.out.println("**************************************************************");
        System.out.println("*                                         " + actualTime() + " ");
        System.out.println("*  1. Registace                                              *");
        System.out.println("*  2. Přihlášení                                             *");
        System.out.println("*  3. Ukončit program                                        *");
        System.out.println("**************************************************************");
        System.out.println("Zadejte volbu: ");
    }

    /**
     * After login GUI
     */
    public static void afterLoginMenu(String name) {
        System.out.println("**************************************************************");
        System.out.println("                      " + "Vítej " + name + " !");
        System.out.println("**************************************************************");
        System.out.println("*                                         " + actualTime() + " ");
        System.out.println("*                                                            *");
        System.out.println("*                       1. Přidat film/seriál                *");
        System.out.println("*                       2. Upravit                           *");
        System.out.println("*                       3. Seřadit a zobrazit                *");
        System.out.println("*                       4. Ukončit program                   *");

        System.out.println("**************************************************************");
        System.out.println("Zadejte volbu: ");
    }

    /**
     * Logic behind creating object
     *
     * @param dtb My dtb
     * @return TVShow or Film object
     */
    public static Watching addShowMenu(Database dtb) {
        System.out.println("Movie-1  nebo Serial-0 ? ");
        String category = (UIMethods.testIntInput1(0, 1) == 1 ? "Movie" : "Serial");
        System.out.println("Zadejte název seriálu/filmu: ");
        sc.nextLine();
        String title = sc.nextLine();
        System.out.println("Již viděno ? 1-ano 0-ne ");
        int watched = UIMethods.testIntInput1(0, 1);
        System.out.println("Délka (v min/seriích)");
        int duration = UIMethods.testIntInput1(1, Integer.MAX_VALUE);
        System.out.println("hodnocení od 1-10");
        int rating = UIMethods.testIntInput1(1, 10);
        System.out.println("Žanry");
        sc.nextLine();
        String genre = sc.nextLine();
        return Database.filmsOrShows(category, title, (watched == 1), rating, duration, dtb.getSize() + 1, genre);
    }

//logic methods

    /**
     * @return a = 1 for registration
     * 2 for login
     * 3 for exit
     */
    public static int login() {
        welcomeScreen();
        return UIMethods.testIntInput1(1, 3);
    }

    /**
     * @param choice choice from menu
     * @return active user
     * @throws IOException err
     * @throws IllegalBlockSizeException err
     * @throws InvalidKeyException err
     * @throws BadPaddingException err
     * @throws NoSuchAlgorithmException err
     * @throws NoSuchPaddingException err
     */
    public static User getActiveUser(int choice) throws IOException, IllegalBlockSizeException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, NoSuchPaddingException {
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

    /**
     * @return ActualTime as String builder
     */
    public static StringBuilder actualTime() {
        LocalDateTime dateTime = LocalDateTime.now();
        StringBuilder myString = new StringBuilder();
        return myString.append(dateTime.format(DateTimeFormatter.ofLocalizedDateTime(FormatStyle.LONG, FormatStyle.SHORT)));
    }

    /**
     * Inf while until i get my wanted int number (works even for string)
     *
     * @param downLim down limit of our wanted number
     * @param upLim   upper limit of our wanted number
     * @return specified int
     */
    public static int testIntInput1(int downLim, int upLim) {
        int tmp;
        while (true) {
            try {
                tmp = Integer.parseInt(sc.next());
                if (tmp <= upLim && tmp >= downLim) {
                    return tmp;
                }
                System.out.println("Hodnota není v daném rozmezí,zkuste znovu");
            } catch (NumberFormatException e) {
                System.out.println("Špatný formát vstupní hodnoty");
            }
        }
    }

    public static Database register(User activeUser) throws IOException {
        if (activeUser != null) {
            Database dtb = Database.loadDTB(Database.getActualPath() +
                    "/FilmsAndSeries/src/utils/netflix_titles.csv", false);
            dtb.saveToMyDTB(activeUser.getName());
            return dtb;
        } else {
            System.out.println("Uživatelské jméno je již zabrané");
        }
        return null;
    }
    public static Database login(User activeUser) throws IOException {
        if (activeUser != null) {
            String path = Database.getActualPath() + "/FilmsAndSeries/src/utils/"
                    + activeUser.getName() + "DTB.csv";
            return Database.loadDTB(path, true);
            //break;
        } else {
            System.out.println("Jméno nebo heslo nejsou správné");
        }
        return null;
    }
    public static void editShow(Database dtb){
        System.out.println("Chcete soubor najít dle indexu -1 nebo názvu -2 ?");
        String title = null;
        int index = -1;
        if (UIMethods.testIntInput1(1, 2) == 1) {
            System.out.println("Zadejte index: ");
            index = UIMethods.testIntInput1(1, dtb.getSize());
        } else {
            System.out.println("Zadejte název: ");
            sc.nextLine();
            title = sc.nextLine();
        }
        for (Watching film : dtb.getDtb()) {
            if ((Integer.parseInt(film.getIndex()) == index) || Objects.equals(title, film.getName())) {
                System.out.println("Už viděno ? 1 - ano 0 - ne");
                film.setWatched((UIMethods.testIntInput1(0, 1) == 1));
                System.out.println("Hodnocení ? 1-10");
                film.setRating(UIMethods.testIntInput1(1, 10));
            }
        }
    }
    public static void filtershowed(Database dtb){
        System.out.println("Seřadit podle hodnocení -0,seřadit a zobrazit pouze již viděné -1");
        if (UIMethods.testIntInput1(0, 1) == 1) {
            System.out.println(dtb.filterWatched());

        } else {
            System.out.println(dtb.sortByRating());
        }
    }

}
