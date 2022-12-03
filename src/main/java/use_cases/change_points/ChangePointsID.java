package use_cases.change_points;

/** The input data that is taken from the client to change points. */
public class ChangePointsID {

    private final int gameID;
    private final int newPoints;
    private final String teamName;

    /**
     * The constructor for the ChangePointsID class.
     * @param gameID The ID of the game.
     * @param newPoints The new points.
     * @param teamName The name of the team.
     */
    public ChangePointsID(int gameID, int newPoints, String teamName) {
        this.gameID = gameID;
        this.newPoints = newPoints;
        this.teamName = teamName;
    }

    /**
     * Gets the team name for the team whose points were changed.
     * @return The game ID.
     */
    public String getTeamNameCPID() {
        return this.teamName;
    }

    /**
     * Gets the game ID for the game whose team's points were changed.
     * @return The game ID.
     */
    public int getGameIDCP() {
        return gameID;
    }

    /**
     * Gets the new points for the team whose points were changed.
     * @return The new points.
     */
    public int getNewPointsCP() {
        return newPoints;
    }
}
