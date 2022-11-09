package useCases.declareWinner;

import entities.*;
import useCases.generalInterfaces.CheckUserPermissionIF;

import java.util.ArrayList;

public class DeclareWinnerUC implements CheckUserPermissionIF {

    public Bracket bracket;
    public User user;
    public String username;
    public Game game;
    public int bracketID;
    public int gameID;

    public void declareWinner(int bracketID, String username, int gameID) {
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

    // Make helper functions private.

    public boolean checkGame(Game game) {
        return this.game != null;
    }

    // I've merged check winner into the set winner method. There is no need to check winner separately.
    // Note that this is also the main method that has to be called for the UC to do its job.
    public boolean setWinner() {
        ArrayList<Team> teams = this.game.getTeams();
        if (checkUserPermission(this.user) && checkGame(this.game)) {
            for (Team team : teams) {
                if (this.game.getTeamPoints(team) == this.bracket.getWinCondition()) {
                    this.game.setWinner(team);
                    this.game.setGameStatus(true);
                    return true;
                }
            }
        } // Think about throwing an appropriate exception here.
        return false;
    }
}
