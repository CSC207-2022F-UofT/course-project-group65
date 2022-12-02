package useCases.createBracket;

import entities.AccountRepo;
import entities.BracketRepo;

import java.util.ArrayList;

/**
 * This class represents the output data for the CreateBracket use case.
 * This data is used in updating/creating the view after the bracket has been
 * successfully created.
 */
public class CreateBracketOD {
    /** The name of the user who create the bracket */
    private final String username;
    /** The account repository */
    private final AccountRepo accounts;
    /** The bracket repository */
    private final BracketRepo brackets;
    /** The ID of the bracket that was created */
    private final int bracketID;
    /** The list of default team names for the teams created in the bracket */
    private final ArrayList<String> teams;

    /**
     * Creates a new CreateBracketOD object.
     * @param username The name of the user who created the bracket
     * @param accounts The account repository
     * @param brackets The bracket repository
     * @param bracketID The ID of the bracket that was created
     * @param teams The list of default team names for the teams created in the bracket
     */
    public CreateBracketOD(String username, AccountRepo accounts, BracketRepo brackets,
                           int bracketID, ArrayList<String> teams){
        this.username = username;
        this.accounts = accounts;
        this.brackets = brackets;
        this.bracketID = bracketID;
        this.teams = teams;

    }

    /**
     * Returns the name of the user who created the bracket.
     * @return The name of the user who created the bracket
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns the account repository.
     * @return The account repository
     */
    public AccountRepo getAccounts() {
        return accounts;
    }

    /**
     * Returns the bracket repository.
     * @return The bracket repository
     */
    public BracketRepo getBrackets() {
        return brackets;
    }

    /**
     * Returns the ID of the bracket that was created.
     * @return The ID of the bracket that was created
     */
    public int getBracketID() {
        return bracketID;
    }

    /**
     * Returns the list of default team names for the teams created in the bracket.
     * @return The list of default team names for the teams created in the bracket
     */
    public ArrayList<String> getTeams() {
        return teams;
    }

}
