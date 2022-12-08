package use_cases.assign_observer;

import entities.BracketRepo;

/**
 * This class is used to save the brackets following assign observer to a file.
 */
public class AssignObserverDSID {
    private final BracketRepo bracketRepo;

    /**
     * Constructor for the ChangePointsDSID class.
     *
     * @param bracketRepo the updated bracket repo
     */
    public AssignObserverDSID(BracketRepo bracketRepo){
        this.bracketRepo = bracketRepo;
    }

    /**
     * Getter for the updated bracket repo.
     *
     * @return the updated bracket repo
     */
    public BracketRepo getBracketRepo(){
        return bracketRepo;
    }
}
