package entities;

import java.util.ArrayList;

public class AccountRepo {
    private ArrayList<User> users;

    public void addUser(User user) {this.users.add(user); }

    public ArrayList<User> getUsers() { return users; }
}
