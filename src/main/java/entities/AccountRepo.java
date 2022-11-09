package entities;

import java.sql.Array;
import java.util.HashMap;
import java.util.ArrayList;

public class AccountRepo {
    private HashMap<String, User> users;

    public void addUser(User user) {this.users.put(user.getUsername(), user); }

    public HashMap<String, User> getUsers() { return users; }

    public ArrayList<String> getAllUsernames() { return new ArrayList<String>(users.keySet()); }

    public ArrayList<String> getAllPasswords() {
        ArrayList<String> passwords = new ArrayList<>();
        for (User user : users.values()) {
            passwords.add(user.getPassword());
        }
        return passwords;
    }
}
