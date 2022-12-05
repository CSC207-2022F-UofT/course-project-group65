package use_cases.join_team;

import entities.BracketRepo;

/**
 * This is a class for accessing data from the Database.
 */
public class JoinTeamDSID {
    /**
     * The bracket repo used in accessing the data
     */
    private final BracketRepo updatedBracketRepo;


    /**
     * Constructor for the JoinTeamDSID class
     *
     * @param updatedBracketRepo - the bracket repo used in accessing the data
     */
    public JoinTeamDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    /**
     * Returns the bracket repo used for accessing the data
     *
     * @return the bracket repo used for accessing the data
     */
    public BracketRepo getUpdatedBracketRepoDSID() {
        return this.updatedBracketRepo;
    }


}
