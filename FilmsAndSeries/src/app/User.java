package app;

import java.io.*;
import java.util.ArrayList;
import java.util.Objects;

public class User {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

    String password;
    String name;

    public User(String name, String password) {
        this.password = password;
        this.name = name;
    }

    public String getName() {
        return name;
    }


    public String getPassword() {
        return password;
    }

    public void setName(String name) {
        this.name = name;
    }
    public void setPassword(String password) {
        this.password = password;
    }

    //methods

    /**
     * Factory method
     * @param name name
     * @param password psw
     * @return New user
     */
    public static User addUser(String name, String password){
        return new User(name,password);
    }

    /**
     * @param name username
     * @param password pswd
     * @return New User, if is the name already used, return null
     * @throws IOException if file cannot be edited
     */
    public static User register(String name,String password) throws IOException { //načíst ntf databázi + přidat vše do vlastní databáze + vytvořit úvodní user a dtbUsers
        String path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/UsersDTB.csv";
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path,true));
        ArrayList<User> users = loadUsers();
        if (isNotThere(users,name)){
            bw.write("\n" + name + ";" + password);
            bw.close();
            return User.addUser(name,password);
        }
        bw.close();
        return null;

    }

    /**
     * @return Arraylist with already registred users
     * @throws IOException if file cannot be readed
     */
    public static ArrayList<User> loadUsers() throws IOException {
        String path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/UsersDTB.csv";
        File usersDTB = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(usersDTB));
        String line;
        ArrayList<User> users = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            User user = User.addUser(parts[0],parts[1]);
            users.add(user);
        }
        return users;
    }
    /**
     *
     * @param users arraylist of users
     * @param name name of new user
     * @return True = if the given name is free
     *         False = if name is already used
     */
    public static boolean isNotThere(ArrayList<User> users,String name){
        for (User a :users) {
            if (Objects.equals(a.getName(),name)){
                return false;
            }
        }
        return true;
    }
    public static void login(){

    }
}
