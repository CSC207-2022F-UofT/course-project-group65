package useCases.teamCreation;

import entities.*;
public class teamCreationID {
    private String teamName;
    private int teamSize;
    private final String userName;
    private final int bracketID;
    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;


    public teamCreationID(String teamName, int teamSize, String userName, int bracketID,
                          BracketRepo bracketRepo, AccountRepo accountRepo) {
        this.teamName = teamName;
        this.teamSize = teamSize;
        this.userName = userName;
        this.bracketID = bracketID;
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

    public String getTeamName(){
        return this.teamName;
    }

    public void setTeamName(String teamName){
        this.teamName = teamName;
    }

    public int getTeamSize(){
        return this.teamSize;
    }

    public void setTeamSize(int teamSize){
        this.teamSize = teamSize;
    }

    public String getUsername(){
        return this.userName;
    }

    public int getBracketID(){
        return this.bracketID;
    }

    public Bracket getBracket() {
        return bracketRepo.getBracket(this.bracketID);
    }

    public User getUser() {
        return accountRepo.getUser(this.userName);
    }
}
