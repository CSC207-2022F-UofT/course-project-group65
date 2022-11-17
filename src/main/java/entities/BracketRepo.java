package entities;

import java.io.Serializable;
import java.util.HashMap;

public class BracketRepo implements Serializable {
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

    public HashMap<Integer, Bracket> getBrackets() {
        return brackets;
    }
}