package useCases.advanceTeam;

import entities.*;

public class AdvanceTeamID {
    private int bracketID;
    private String username;
    private int gameID;
    private BracketRepo bracketRepo;
    private AccountRepo accountRepo;


    public AdvanceTeamID(int bracketID, String username, int gameID, BracketRepo bracketRepo, AccountRepo accountRepo) {
        this.bracketID = bracketID;
        this.username = username;
        this.gameID = gameID;
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

//    public int getBracketID() {
//        return bracketID;
//    }
//
//    public String getUsername() {
//        return username;
//    }

    public void setBracketID(int bracketID) {
        this.bracketID = bracketID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public Bracket getBracket() {
        return bracketRepo.getBracket(this.bracketID);
    }

    public User getUser() {
        return accountRepo.getUser(this.username);
    }
}
