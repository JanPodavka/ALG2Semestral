package ui;

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
    public static int Login() {
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



}
