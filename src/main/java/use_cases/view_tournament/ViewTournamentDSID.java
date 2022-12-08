package use_cases.view_tournament;

import entities.BracketRepo;

/**
 * This class is used to save the brackets following view tournament to a file.
 */
public class ViewTournamentDSID {
    private final BracketRepo bracketRepo;

    /**
     * Constructor for the ChangePointsDSID class.
     *
     * @param bracketRepo the updated bracket repo
     */
    public ViewTournamentDSID(BracketRepo bracketRepo){
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
