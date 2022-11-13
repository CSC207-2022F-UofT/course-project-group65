package useCases.createBracket;

import entities.AccountRepo;
import entities.BracketRepo;

import java.util.ArrayList;

public class CreateBracketOD {
    /*
    The output data class for the create bracket output boundary.
     */
    private String username;
    private AccountRepo accounts;
    private BracketRepo brackets;
    private String bracketType;

    private int bracketID;

    private ArrayList<String> teams;


    public CreateBracketOD(String username, AccountRepo accounts, BracketRepo brackets, String bracketType, int bracketID, ArrayList<String> teams) {
        this.username = username;
        this.accounts = accounts;
        this.brackets = brackets;
        this.bracketType = bracketType;
        this.bracketID = bracketID;
        this.teams = teams;
    }

    public String getUsername() {
        return username;
    }

    public AccountRepo getAccounts() {
        return accounts;
    }

    public BracketRepo getBrackets() {
        return brackets;
    }

    public String getBracketType() {
        return bracketType;
    }

    public int getBracketID() {
        return bracketID;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }
}
