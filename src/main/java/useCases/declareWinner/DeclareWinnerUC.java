package useCases.declareWinner;

import entities.*;
import useCases.generalInterfaces.CheckUserPermissionIF;

import java.util.ArrayList;

public class DeclareWinnerUC implements CheckUserPermissionIF {

    public Bracket bracket;
    public int newPoints;
    public Team team;
    public User user;
    public String username;
    public Game game;
    public int bracketID;
    public int gameID;

    public void declareWinner(int bracketID, Team team, String username, int gameID) {
        this.team = team;
        this.username = username;
        this.bracketID = bracketID;
        this.gameID = gameID;
    }

    public void changePoints(int bracketID, Team team, String username, int gameID, int points) {
        this.newPoints = points;
        this.team = team;
        this.username = username;
        this.bracketID = bracketID;
        this.gameID = gameID;
    }
    // When this use case is instantiated, you also have to call the following three methods. This is so user, bracket,
    // and game can take the correct values.
    public void findUser(AccountRepo accountRepo) {
        ArrayList<User> users = accountRepo.getUsers();
        for (User user : users) {
            if (user.getUsername().equals(username)) {
                this.user = user;
            }
        }
    }

    public void findBracket(BracketRepo bracketRepo) {
        this.bracket = bracketRepo.getBracket(this.bracketID);
    }

    public void findGame(int gameID, Game head) {
        if (head == null) {
            return;
        } else if (head.getGameID() == gameID) {
            this.game = head;
        } else {
            findGame(gameID, head.getPrevGame1());
            findGame(gameID, head.getPrevGame2());
        }
    }

    public boolean checkUserPermission(User user) {
        String userRole = user.getBracketRole(this.bracket.getTournamentID());
        User assignedObserver = this.game.getObserver();
        String username = user.getUsername();
        String assignedObserverUsername = assignedObserver.getUsername();

        if (userRole.equals("Overseer")) {
            return true;
        } else if (userRole.equals("Observer")) {
            return username.equals(assignedObserverUsername);
        } else {
            return false;
        }
    }


}
