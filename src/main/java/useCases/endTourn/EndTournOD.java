package useCases.endTourn;

import entities.Bracket;
import entities.User;

public class EndTournOD {
    private Bracket bracket;
    private User user;
    public EndTournOD(Bracket bracket, User user) {
        this.bracket = bracket;
        this.user = user;
    }

    public Bracket getBracket() {
        return this.bracket;
    }

    public User getUser() { return this.user; }
}
