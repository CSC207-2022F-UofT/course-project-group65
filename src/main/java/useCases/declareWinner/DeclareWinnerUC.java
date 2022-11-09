package useCases.declareWinner;

import entities.Bracket;
import entities.Game;
import entities.Team;
import entities.User;
import useCases.generalInterfaces.CheckUserPermissionIF;

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
