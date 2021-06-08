package ui;

import app.User;
import app.Database;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.*;


public class UIApp {

    public static void main(String[] args) throws IOException, NumberFormatException, InputMismatchException, InvalidKeyException, BadPaddingException, NoSuchAlgorithmException, IllegalBlockSizeException, NoSuchPaddingException {
        int choice;
        Database dtb = new Database();
        User activeUser; // variable init
        try {
            dtb.initDTBs();//check if user file already existed,if not create admin accountS
            do {
                choice = UIMethods.login();
                activeUser = UIMethods.getActiveUser(choice);
                switch (choice) {
                    case 1:
                        dtb = UIMethods.register(activeUser);
                        break;
                    case 2:
                        dtb = UIMethods.login(activeUser);
                        break;
                    case 3:
                        return;
                }
            } while (activeUser == null);
            do {
                UIMethods.afterLoginMenu(activeUser.getName()); // Next text menu
                choice = UIMethods.testIntInput1(1, 4);
                assert dtb != null;
                switch (choice) {
                    case 1 -> dtb.add(UIMethods.addShowMenu(dtb)); //add film/tv show to dtb
                    case 2 -> UIMethods.editShow(dtb);
                    case 3 -> UIMethods.filtershowed(dtb);
                }
            } while (choice != 4);
            dtb.saveToMyDTB(activeUser.getName()); //normální řazení
        }catch (Exception e){
            System.out.println("Chyba v načítání/ukládání souboru");
        }
    }
    }


