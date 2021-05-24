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



    public static void welcomeScreen(){
        System.out.println("**************************************************************");
        System.out.println("*                  Databáze Seriálů a filmů                  *");
        System.out.println("*  1. Registace                                              *");
        System.out.println("*  2. Přihlášení                                             *");
        System.out.println("*  3. Ukončit program                                        *");
        System.out.println("**************************************************************");
        System.out.println("Zadejte volbu: ");
    }
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
    public static User loginMenu(int choice) throws IOException {
        String name;
        String password;
        switch (choice) {
            case 1,2 -> {                 //funkce pro načtení ntf databaze + založení vlastní dtb
                System.out.println("Zadej jméno");
                name = sc.next();
                System.out.println("Zadej heslo");
                password = sc.next();
                User activeUser = User.register(name,password,choice);
                if(activeUser != null){ //succesful login or register
                    System.out.println("Vítej " + activeUser.getName() + " !");
                }
                else if(choice == 1){ //error if user is already registred
                    System.out.println("Uživatelské jméno je již zabrané !");
                }
                else{
                    System.out.println("Špatné uživatelské jméno nebo heslo");
                }
                //metoda kde se vytvoří uživatelova(název souboru se jmenem dtb a načte se k ní ntf databáze
                return activeUser;
            }
            case 3 -> {
                System.out.println("program ukončen");
                return null;
            }
        }
        return null;
    }


}
