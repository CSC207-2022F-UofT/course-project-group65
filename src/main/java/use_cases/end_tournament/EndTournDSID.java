package use_cases.end_tournament;

import entities.BracketRepo;

/**
 * This is a class for accessing the input data from the Database.
 */
public class EndTournDSID {
    /** The bracket repo used in accessing the data */
    BracketRepo bracketRepo;

    /**
     * Constructor for the EndTournDSID class.
     * @param bracketRepo the bracket repository used to access the data
     */
    public EndTournDSID(BracketRepo bracketRepo) {
        this.bracketRepo = bracketRepo;
    }

    /**
     * @return the bracket repo used for accessing the data
     */
    public BracketRepo getBracketRepo() {return this.bracketRepo;}
}
