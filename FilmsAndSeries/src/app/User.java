package app;

import javax.crypto.*;
import javax.crypto.spec.SecretKeySpec;
import java.io.*;
import java.security.InvalidKeyException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Base64;
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
    public static User register(String name, String password, int choice) throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Key aesKey = new SecretKeySpec(Encryption.key.getBytes(), "AES"); //for ecryption
        Cipher cipher = Cipher.getInstance("AES"); //vytvoří instanci cipher
        String path = Database.getActualPath() + "/FilmsAndSeries/src/utils/UsersDTB.csv";
        BufferedWriter bw = new BufferedWriter(
                new FileWriter(path, true));
        ArrayList<User> users = loadUsers();
        byte[] secret = Encryption.encrypt(password, cipher, aesKey); //zde se zašifruje heslo
        String cipheredPSW = Base64.getEncoder().encodeToString(secret);
        if (isNotThere(users, name) && choice == 1) { //if name is free and i want to register
            bw.write("\n" + name + ";" + cipheredPSW);
            bw.close();
            return User.addUser(name, password);
        } else if (verify(users, name, cipheredPSW) && choice == 2) { //if is already existed and you want to login, only return you
            bw.close();
            return User.addUser(name, password);
        }
        bw.close();
        return null;

    }

    /**
     * @return Arraylist with already registred users
     * @throws IOException if file cannot be readed
     */
    public static ArrayList<User> loadUsers() throws IOException, NoSuchPaddingException, NoSuchAlgorithmException, BadPaddingException, InvalidKeyException, IllegalBlockSizeException {
        Key aesKey = new SecretKeySpec(Encryption.key.getBytes(), "AES"); //for ecryption
        Cipher cipher = Cipher.getInstance("AES");
        String path = Database.getActualPath() + "/FilmsAndSeries/src/utils/UsersDTB.csv";
        File usersDTB = new File(path);
        BufferedReader br = new BufferedReader(new FileReader(usersDTB));
        String line;
        ArrayList<User> users = new ArrayList<>();
        while ((line = br.readLine()) != null) {
            String[] parts = line.split(";");
            byte[] decodedKey = Base64.getDecoder().decode(parts[1]);
            SecretKey originalKey = new SecretKeySpec(decodedKey, 0, decodedKey.length, "AES");
            Encryption.decrypt(originalKey.getEncoded(), aesKey, cipher);
            User user = User.addUser(parts[0], parts[1]);
            users.add(user);
        }
        br.close();
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

    /**
     * if psw and name are correct,return true,otherwise correct
     *
     * @param users    Arraylisto of users
     * @param name     name of actual user
     * @param password psw of actual user
     * @return true if name and psw is correct
     */
    public static boolean verify(ArrayList<User> users, String name, String password) {
        for (User a : users) {
            if (Objects.equals(a.getName(), name) && Objects.equals(a.getPassword(), password)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "Uživatel " + name + " má uživatelské heslo: " + password;
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

    public void setPassword(String password) { //later for forget password
        this.password = password;
    }
}

