package ui;

import app.Sorting;
import app.User;
import app.Watching;
import app.WorkWithDTB;

import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Objects;
import java.util.Scanner;

public class UIApp {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */
    public static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) throws IOException, NumberFormatException,InputMismatchException{
        WorkWithDTB.initDTBs(); //check if user file already existed,if not create admin accountS
        int choice;
        ArrayList<Watching> dtb = null;
        User activeUser;
        String path;
        //***********************
        do {
            choice = UILogic.login();
            activeUser = UILogic.getActiveUser(choice);
            if (choice == 1 ) {
                    if(activeUser != null){
                        dtb = WorkWithDTB.loadDTB(WorkWithDTB.getActualPath() +
                                "/FilmsAndSeries/src/utils/netflix_titles.csv", false);
                        assert dtb != null;
                        WorkWithDTB.saveToMyDTB(dtb, activeUser.getName());
                        break;

                    }else{
                        System.out.println("Uživatelské jméno je již zabrané");
                    }

            }
            if (choice == 2) {
                    if (activeUser != null){
                        path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/"
                                + activeUser.getName() + "DTB.csv";
                        dtb = WorkWithDTB.loadDTB(path,true);
                        break;

                    }else{
                        System.out.println("Jméno nebo heslo nejsou správné");
                    }

            }
            if (choice == 3) {
                return;
            }
        } while (activeUser == null);
        //*********************
        choice = 0;
        do {
            UILogic.afterLoginMenu(activeUser.getName()); // Next text menu
            int index = 0;
            String title = null;
                try {
                    choice = sc.nextInt();
                    if (choice == 1) {
                        assert dtb != null;
                        dtb.add(UILogic.addShowMenu(dtb)); //add film/tv show to dtb
                    } else if (choice == 2) {
                        System.out.println("Chcete soubor najít dle indexu -1 nebo názvu -2 ?");
                        index = -1;
                        title = null;
                        if (sc.nextInt() == 1) {
                            System.out.println("Zadejte index: ");
                            index = sc.nextInt();
                        } else {
                            System.out.println("Zadejte název: ");
                            sc.nextLine();
                            title = sc.nextLine();
                        }
                    }else if(choice == 3){
                        System.out.println("Seřadit podle hodnocení -0,seřadit a zobrazit pouze již viděné -1");
                        if(sc.nextInt()==1){
                            assert dtb != null;
                            System.out.println(Sorting.filterWatched(dtb));

                        }else{
                            System.out.println(Sorting.sortByRating(dtb));
                        }
                    }
                }catch (InputMismatchException e){
            e.printStackTrace();
                }
                assert dtb != null;
                for (Watching film:dtb) {
                    if ((Integer.parseInt(film.getIndex()) == index) || Objects.equals(title,film.getName())){
                        System.out.println("Už viděno ? 1 - ano 0 - ne");
                        film.setWatched((sc.nextInt() == 1));
                        System.out.println("Hodnocení ? 1-10");
                        film.setRating(sc.nextInt());
                        }
                    }
        } while (choice != 4);
        // WorkWithDTB.saveToMyDTB(dtb,activeUser.getName()); //normální řazení
        ArrayList<Watching> sortedDTB = Sorting.sortByRating(dtb); //--test pro řazení dle hodnocení
        //WorkWithDTB.saveToMyDTB(sortedDTB,activeUser.getName()); sorted řazení

    }
}

