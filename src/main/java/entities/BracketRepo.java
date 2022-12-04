package entities;

import java.io.Serializable;
import java.util.HashMap;
/** This Bracket repository class is for storing all the brackets*/
public class BracketRepo implements Serializable {
    /** The hashmap to store all the brackets*/
    private HashMap<Integer, Bracket> brackets;
    /** Creat a new hashmap called brackets */
    public BracketRepo() {
        brackets = new HashMap<Integer, Bracket>();
    }
    /** The method for adding the bracket to the hashmap
     * @param bracket - The bracket */
    public void addBracket(Bracket bracket) {
        brackets.put(bracket.getTournamentID(), bracket);
    }
    /** The getter method for bracket by
     * @param bracketID - The ID of the bracket*/
    public Bracket getBracket(int bracketID) {
        return brackets.get(bracketID);
    }
    /** The getter method for bracketRepo */
    public BracketRepo getBracketRepo() {
        return this;
    }
    /** The getter method for all brackets*/
    public HashMap<Integer, Bracket> getBrackets() {
        return brackets;
    }
}