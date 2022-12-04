package use_cases.change_points;

import entities.BracketRepo;

/**
 * This class is used to save the brackets following change points to a file.
 */
public class ChangePointsDSID {

    private final BracketRepo updatedBracketRepo;

    /**
     * Constructor for the ChangePointsDSID class.
     *
     * @param updatedBracketRepo the updated bracket repo
     */
    public ChangePointsDSID(BracketRepo updatedBracketRepo) {
        this.updatedBracketRepo = updatedBracketRepo;
    }

    /**
     * Getter for the updated bracket repo.
     *
     * @return the updated bracket repo
     */
    public BracketRepo getUpdatedBracketRepoCP() {
        return this.updatedBracketRepo;
    }

}
