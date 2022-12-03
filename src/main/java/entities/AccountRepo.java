package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;

public class AccountRepo implements Serializable {
    private final HashMap<String, User> users = new HashMap<>();

    public void addUser(User user) {this.users.put(user.getUsername(), user); }

    public HashMap<String, User> getAllUsers() { return users; }

    public User getUser(String username) {return users.get(username); }

    public ArrayList<String> getAllUsernames() { return new ArrayList<>(users.keySet()); }
    public AccountRepo getAccountRepo() { return this; }

    public ArrayList<String> getAllPasswords() {
        ArrayList<String> passwords = new ArrayList<>();
        for (User user : users.values()) {
            passwords.add(user.getPassword());
        }
        return passwords;
    }

}
