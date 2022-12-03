package use_cases.declare_winner;

import entities.BracketRepo;

/** The database input data/request model for the DeclareWinner use case. */
public class DeclareWinnerDSID {

    private final BracketRepo updatedBracketRepo;

    /** The constructor for the DeclareWinnerDSID class.
     * @param updatedBracketRepo the updated bracket repository
     */
    public DeclareWinnerDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    /** Returns the updated bracket repository after a winner is declared.
     * @return the updated bracket repository. */
    public BracketRepo getUpdatedBracketRepoDSID() {
        return this.updatedBracketRepo;
    }
}
