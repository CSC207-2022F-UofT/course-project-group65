package entities;

import java.io.Serializable;
import java.util.HashMap;
import java.util.ArrayList;
/** This Account repository class is for storing the user's accounts' information - username and passwords*/
public class AccountRepo implements Serializable {
    /** The hashmap to store all the users*/
    private final HashMap<String, User> users = new HashMap<>();
    /** This method is for adding users to the hashmap*/
    public void addUser(User user) {this.users.put(user.getUsername(), user); }
    /** This method is for getting all the current users*/
    public HashMap<String, User> getAllUsers() { return users; }
    /** This method is for getting one specific user by
     * @param username - the username of the user*/
    public User getUser(String username) {return users.get(username); }
    /** This method is for getting all the current users' usernames*/
    public ArrayList<String> getAllUsernames() { return new ArrayList<>(users.keySet()); }
    /** This method is for getting the current accountRepo*/
    public AccountRepo getAccountRepo() { return this; }
    /** This method is for getting all the current users' passwords*/
    public ArrayList<String> getAllPasswords() {
        ArrayList<String> passwords = new ArrayList<>();
        for (User user : users.values()) {
            passwords.add(user.getPassword());
        }
        return passwords;
    }

}
