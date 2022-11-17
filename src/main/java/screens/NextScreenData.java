package screens;

import entities.AccountRepo;
import entities.BracketRepo;

public class NextScreenData {
//    Currently not in use, will remove if it is not needed.
    private String currentUser;
    private int currentBracketID;
    private AccountRepo accounts;
    private BracketRepo brackets;

    public NextScreenData() {
        this.currentUser = "";
        this.currentBracketID = 0;
        this.accounts = null;
        this.brackets = null;
    }

    public void setCurrentUser(String username) {
        this.currentUser = username;
    }

    public String getCurrentUser() {
        return this.currentUser;
    }

    public void setCurrentBracketID(int bracketID) {
        this.currentBracketID = bracketID;
    }

    public int getCurrentBracketID() {
        return this.currentBracketID;
    }

    public void setAccounts(AccountRepo accounts) {
        this.accounts = accounts;
    }

    public AccountRepo getAccounts() {
        return this.accounts;
    }

    public void setBrackets(BracketRepo brackets) {
        this.brackets = brackets;
    }

    public BracketRepo getBrackets() {
        return this.brackets;
    }
}
