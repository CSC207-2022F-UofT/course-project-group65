package use_cases.advance_team;

/** The input data that is passed into the use case */
public class AdvanceTeamID {
    private final int gameID;

    /** Creates a new input data instance for the advance team use case. */

    public AdvanceTeamID(int gameID) {
        this.gameID = gameID;
    }

    /** Returns the game ID of the game that the user wants to advance a team from. */

    public int getGameIDAT() {
        return gameID;
    }
}
