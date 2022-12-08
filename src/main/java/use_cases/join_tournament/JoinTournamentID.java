package use_cases.join_tournament;

/**
 * The input data that is taken from the client to join tournament.
 */
public class JoinTournamentID {
    private final String tournamentID;

    /**
     * The constructor for the AssignObserverID class.
     *
     * @param invite    An invite for the tournament
     */
    public JoinTournamentID(String invite) {
        tournamentID = invite;
    }

    /**
     * Gets the invite for the tournament the user wants to join.
     *
     * @return The invite.
     */
    String getInvite() {
        return tournamentID;
    }
}
