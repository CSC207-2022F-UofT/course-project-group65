package useCases.startTourn;

import entities.Bracket;

public class StartTournOD {
    private Bracket bracket;
    public StartTournOD(Bracket bracket) {
        this.bracket = bracket;
    }

    public Bracket getBracket() {
        return this.bracket;
    }
}
