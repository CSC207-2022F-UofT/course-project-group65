package useCases.startTourn;

import entities.Bracket;
import entities.User;

public class StartTournOD {
    private Bracket bracket;
    private User user;
    public StartTournOD(Bracket bracket, User user) {
        this.bracket = bracket;
        this.user = user;
    }

    public Bracket getBracket() {
        return this.bracket;
    }

    public User getUser() {
        return this.user;
    }
}
