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

    /**
     * Factory method
     *
     * @param name     name
     * @param password psw
     * @return New user
     */
    public static User addUser(String name, String password) {
        return new User(name, password);
    }

    /**
     * @param name     username
     * @param password pswd
     * @return New User, if is the name already used, return null
     * @throws IOException if file cannot be edited
     */
    public static User register(String name, String password, int choice) throws IOException {
        String path = WorkWithDTB.getActualPath() + "/FilmsAndSeries/src/utils/UsersDTB.csv";
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path, true));
        ArrayList<User> users = loadUsers();
        if (isNotThere(users, name) && choice == 1) { //if name is free and i want to register
            bw.write("\n" + name + ";" + password);
            bw.close();
            return User.addUser(name, password);
        } else if (verify(users,name,password) && choice == 2) {
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
            User user = User.addUser(parts[0], parts[1]);
            users.add(user);
        }
        return users;
    }

    /**
     * @param users arraylist of users
     * @param name  name of new user
     * @return True = if the given name is free
     * False = if name is already used
     */
    public static boolean isNotThere(ArrayList<User> users, String name) {
        for (User a : users) {
            if (Objects.equals(a.getName(), name)) {
                return false;
            }
        }
        return true;
    }

    //methods

    public static boolean verify(ArrayList<User> users, String name, String password) {
        for (User a : users) {
            if (Objects.equals(a.getName(), name) && Objects.equals(a.getPassword(), password)) {
                return true;
            }
        }
        return false;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}

