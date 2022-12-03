package use_cases.declare_winner;

/** This is the input data that is passed into the DeclareWinner use case. */
public class DeclareWinnerID {

    private final int gameID;

    /**
     * @param gameID the ID of the game that is being declared a winner. */
    public DeclareWinnerID(int gameID) {
        this.gameID = gameID;
    }


    /** @return the ID of the game that is being declared a winner. */
    public int getGameIDDW() {
        return gameID;
    }

}
