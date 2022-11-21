package useCases.teamCreation;
import entities .*;

import java.util.ArrayList;

public class teamCreationOD {
    private String success;
    private ArrayList<ArrayList<String>> teamMembers;
    private ArrayList<String> teams;
    private String username;
    private int bracketID;
    private AccountRepo accounts;
    private BracketRepo brackets;

    public teamCreationOD(ArrayList<ArrayList<String>> teamMembers, ArrayList<String> teams, String success,
                          String username, int bracketID, AccountRepo accounts, BracketRepo brackets){
        this.success = success;
        this.teamMembers = teamMembers;
        this.teams = teams;
        this.username = username;
        this.bracketID = bracketID;
        this.accounts = accounts;
        this.brackets = brackets;

    }

    public String getSuccess(){
        return this.success;
    }

    public void setSuccess(String success){
        this.success = success;
    }

    public ArrayList<String> getTeams() {
        return teams;
    }

    public void setTeams(ArrayList<String> teams){
        this.teams = teams;
    }

    public ArrayList<ArrayList<String>> getTeamMembers() {
        return teamMembers;
    }

    public void setTeamMembers(ArrayList<ArrayList<String>> teamMembers){
        this.teamMembers = teamMembers;
    }

    public String getUsername(){
        return username;
    }

    public void setUsername(String username){
        this.username = username;
    }

    public int getBracketID(){
        return bracketID;
    }

    public void setBracketID(int bracketID){
        this.bracketID = bracketID;
    }
    public AccountRepo getAccounts(){
        return accounts;
    }
    public void setAccounts(AccountRepo accounts){
        this.accounts =  accounts;
    }
    public BracketRepo getBrackets(){
        return brackets;
    }

    public void setBrackets(BracketRepo brackets){
        this.brackets = brackets;
    }
}
