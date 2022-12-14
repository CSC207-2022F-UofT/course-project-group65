package use_cases.declare_winner;

/**
 * The output data for the DeclareWinnerUC use case, that will be passed to the view.
 */
public class DeclareWinnerOD {
    private final int gameID;
    private final String winningTeamName;

    /**
     * Construct a DeclareWinnerOD instance with the given winning team name.
     *
     * @param winningTeamName The name of the winning team
     */
    public DeclareWinnerOD(int gameID, String winningTeamName) {
        this.winningTeamName = winningTeamName;
        this.gameID = gameID;
    }

    /**
     * Get the name of the winning team.
     *
     * @return The name of the winning team
     */
    public String getWinningTeamName() {
        return winningTeamName;
    }

    public int getGameID() {
        return gameID;
    }
}
