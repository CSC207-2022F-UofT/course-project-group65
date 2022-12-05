package use_cases.general_classes;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;

import java.util.ArrayList;
import java.util.HashMap;

public class InformationRecord {
    private AccountRepo accountData;
    private BracketRepo bracketData;

    public InformationRecord(Object accountData, Object bracketData) {
        try{
            this.accountData = (AccountRepo) accountData;
            this.bracketData = (BracketRepo) bracketData;
        } catch (Exception e) {
            System.out.println("Error in InformationRecord");
        }
    }

    public AccountRepo getAccountData() {
        return accountData;
    }

    public BracketRepo getBracketData() {
        return bracketData;
    }
    public int getMaxTeamSize(int bracketID){
        return this.bracketData.getBracket(bracketID).getTeamSize();
    }

    //Account Repo Methods

    public void addUser(User user) {
        accountData.addUser(user);
    }

    public User getUser(String username) {
        return accountData.getUser(username);
    }

    public ArrayList<Integer> getUserTournaments(String username) {
        return (ArrayList<Integer>) getUser(username).getAllTournaments();
    }

    public String getUserPassword(String username) {
        return getUser(username).getPassword();
    }

    //Bracket Repo Methods

    public void addBracket(Bracket bracket) {
        bracketData.addBracket(bracket);
    }

    public Bracket getBracket(int bracketID) {
        return bracketData.getBracket(bracketID);
    }

    public HashMap<Integer, Bracket> getBrackets() {
        return bracketData.getBrackets();
    }

}
