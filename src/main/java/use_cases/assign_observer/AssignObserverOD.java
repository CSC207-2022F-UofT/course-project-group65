package use_cases.assign_observer;

import java.util.LinkedHashMap;

/**
 * The output data of the assign observer use case.
 */
public class AssignObserverOD {
    private final String assignee;
    private final int gameID;
    private final LinkedHashMap<Integer, String> gameToReferee;

    /**
     * The constructor of the output data of the assign observer use case.
     *
     * @param assignee      The username of the assignee.
     * @param gameID        The game the observer was assigned to
     * @param gameToReferee Map from a game ID to its observer
     */
    public AssignObserverOD(String assignee, int gameID, LinkedHashMap<Integer, String> gameToReferee) {
        this.assignee = assignee;
        this.gameID = gameID;
        this.gameToReferee = gameToReferee;
    }

    /**
     * Returns a map of games to referee.
     * @return The map of games to referee
     */
    public LinkedHashMap<Integer, String> getGameToReferee() {
        return gameToReferee;
    }

    /**
     * Returns the ID of the game an observer was assigned to.
     * @return The game ID
     */
    public int getGameID() {
        return gameID;
    }

    /**
     * Returns the username of the assignee.
     * @return The username of the assignee
     */
    public String getAssignee() {
        return assignee;
    }
}
