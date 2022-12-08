package use_cases.assign_observer;

/**
 * The input data that is taken from the client to assign observer.
 */
public class AssignObserverID {
    private final String assignee;
    private final int gameID;

    /**
     * The constructor for the AssignObserverID class.
     *
     * @param gameID    The ID of the game.
     * @param assignee  The username of the assignee user
     */
    public AssignObserverID(String assignee, int gameID){
        this.assignee = assignee;
        this.gameID = gameID;
    }

    /**
     * Gets the username for the user who was assigned to a game.
     *
     * @return The assignee.
     */
    String getAssignee() {
        return assignee;
    }

    /**
     * Gets the game ID for the game that had a user added to it.
     *
     * @return The game ID.
     */
    int getGameID() {
        return gameID;
    }
}
