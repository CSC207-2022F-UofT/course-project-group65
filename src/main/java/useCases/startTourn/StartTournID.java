package useCases.startTourn;

import entities.Bracket;
import entities.Game;
import entities.User;

import java.util.List;

public class StartTournID {
    private User user;
    private Bracket bracket;



    public StartTournID(User user, Bracket bracket) {
        this.user = user;
        this.bracket = bracket;
    }

    public Bracket getBracket() {
        return this.bracket;
    }

    public String getUserRole() {
        return user.getBracketRole(bracket.getTournamentID());
    }

    public int getMaxNumTeams() {
        return this.bracket.getTeamSize();
    }

    public Game getFinalGame() {
        return this.bracket.getFinalGame();
    }

    public List getTeams() {
        return this.bracket.getTeams();
    }
}
