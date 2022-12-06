package use_cases.start_tournament;

import entities.BracketRepo;

/**
 * This is a class for accessing the input data from the Database.
 */
public class StartTournDSID {
    /**
     * The bracket repo used in accessing the data
     */
    private final BracketRepo bracketRepo;

    /**
     * Constructor for the StartTournDSID class.
     *
     * @param bracketRepo the bracket repository used to access the data
     */
    public StartTournDSID(BracketRepo bracketRepo) {
        this.bracketRepo = bracketRepo;
    }

    /**
     * Returns the bracket repo used for accessing the data
     *
     * @return the bracket repo used for accessing the data
     */
    public BracketRepo getBracketRepo() {
        return this.bracketRepo;
    }
}
