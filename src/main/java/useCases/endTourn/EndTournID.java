package useCases.endTourn;


import entities.Bracket;
import entities.Game;
import entities.Team;
import entities.User;

import java.util.List;

public class EndTournID {
    private User user;
    private Bracket bracket;

    public EndTournID(User user, Bracket bracket) {
        this.user = user;
        this.bracket = bracket;
    }

    public String getUserRole() {
        return this.user.getBracketRole(this.bracket.getTournamentID());
    }

    public Bracket getBracket() {
        return this.bracket;
    }

    public Game getFinalGame() { return this.bracket.getFinalGame(); }

    public Team getFinalWinner() {
        return this.bracket.getFinalGame().getWinner();
    }

    public List getTeams() { return this.bracket.getTeams(); }
}

