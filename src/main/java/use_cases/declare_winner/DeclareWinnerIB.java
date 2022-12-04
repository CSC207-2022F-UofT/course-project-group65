package use_cases.declare_winner;

/**
 * The input boundary for the DeclareWinner use case.
 */
public interface DeclareWinnerIB {

    /**
     * The input boundary through which the DeclareWinner use case is invoked.
     *
     * @param declareWinnerID The ID of the DeclareWinner use case.
     * @return The output data.
     */
    DeclareWinnerOD setWinner(DeclareWinnerID declareWinnerID);

}
