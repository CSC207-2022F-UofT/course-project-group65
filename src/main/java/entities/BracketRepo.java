package entities;

import java.util.HashMap;

public class BracketRepo {
    private HashMap<Integer, Bracket> brackets;

    public BracketRepo() {
        brackets = new HashMap<Integer, Bracket>();
    }

    public void addBracket(Bracket bracket) {
        brackets.put(bracket.getTournamentID(), bracket);
    }

    public Bracket getBracket(int bracketID) {
        return brackets.get(bracketID);
    }
}