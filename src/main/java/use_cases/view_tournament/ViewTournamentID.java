package use_cases.view_tournament;

/**
 * The input data that is taken from the client to view tournament.
 */
public class ViewTournamentID {
    private final int tournamentID;

    /**
     * The constructor for the AssignObserverID class.
     *
     * @param tournamentID    The ID of the tournament
     */
    public ViewTournamentID(int tournamentID){
        this.tournamentID = tournamentID;
    }

    /**
     * Gets the ID for the tournament the user wants to view.
     *
     * @return The tournament ID.
     */
    int getTournamentID() {
        return tournamentID;
    }
}
