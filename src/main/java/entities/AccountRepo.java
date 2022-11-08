package entities;

import java.util.HashMap;

import java.util.ArrayList;

public class AccountRepo {
    private ArrayList<String> usernames;
    private ArrayList<String> passwords;

    public void addUsername(String username) {this.usernames.add(username); }

    public ArrayList<String> getUsernames() { return usernames; }

    public void addPasswords(String password) {this.passwords.add(password);}

    public ArrayList<String> getPasswords() { return passwords; }
}
