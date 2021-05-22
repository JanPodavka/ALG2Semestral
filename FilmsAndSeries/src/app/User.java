package app;

public class User {
    /*
    doc
    @Jan Podávka
    https://github.com/JanPodavka
    */

    String password;
    int index;
    String name;

    public User(String password, int index, String name) {
        this.password = password;
        this.index = index;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getIndex() {
        return index;
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
}
