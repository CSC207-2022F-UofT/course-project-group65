package useCases.endTourn;

import entities.Bracket;

public class EndTournOD {
    private Bracket bracket;
    public EndTournOD(Bracket bracket) {
        this.bracket = bracket;
    }

    public Bracket getBracket() {
        return this.bracket;
    }
}
