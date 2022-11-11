package useCases.declareWinner;

import entities.AccountRepo;
import entities.Bracket;
import entities.BracketRepo;
import entities.User;

public class DeclareWinnerID {

    private int bracketID;
    private String username;
    private int gameID;
    private BracketRepo bracketRepo;
    private AccountRepo accountRepo;

    public DeclareWinnerID(int bracketID, String username, int gameID, BracketRepo bracketRepo, AccountRepo accountRepo) {
        this.bracketID = bracketID;
        this.username = username;
        this.gameID = gameID;
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

    public void setBracketID(int bracketID) {
        this.bracketID = bracketID;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameIDDW() {
        return gameID;
    }

    public Bracket getBracketDW() {
        return bracketRepo.getBracket(this.bracketID);
    }

    public User getUserDW() {
        return accountRepo.getUser(this.username);
    }

}
