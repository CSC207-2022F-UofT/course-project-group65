package useCases.changePoints;
import entities.*;

public class ChangePointsID {

    private int bracketID;
    private String username;
    private int gameID;
    private int newPoints;
    private BracketRepo bracketRepo;
    private AccountRepo accountRepo;
    private Team team;

    public ChangePointsID(int bracketID, String username, int gameID, int newPoints, BracketRepo bracketRepo,
                          AccountRepo accountRepo, Team team) {
        this.bracketID = bracketID;
        this.username = username;
        this.gameID = gameID;
        this.newPoints = newPoints;
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
        this.team = team;
    }

    public void setBracketIDCP(int bracketID) {
        this.bracketID = bracketID;
    }

    public void setUsernameCP(String username) {
        this.username = username;
    }

    public void setGameIDCP(int gameID) {
        this.gameID = gameID;
    }

    public void setNewPoints(int newPoints) {
        this.newPoints = newPoints;
    }

    public void setTeamCP(Team team) {
        this.team = team;
    }

    public int getGameIDCP() {
        return gameID;
    }

    public Bracket getBracketCP() {
        return bracketRepo.getBracket(this.bracketID);
    }

    public User getUserCP() {
        return accountRepo.getUser(this.username);
    }

    public int getNewPointsCP() {
        return newPoints;
    }

    public Team getTeamCP() {
        return team;
    }

}
