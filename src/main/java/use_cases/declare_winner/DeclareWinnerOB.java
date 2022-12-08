package use_cases.declare_winner;

/**
 * The DeclareWinnerOB class is the object boundary class for the DeclareWinner use case.
 */
public interface DeclareWinnerOB {

    /**
     * Defines the method that will pass one the output data to the view given a success
     *
     * @param outputData the output data to be passed to the view
     * @return the output data to be passed to the view
     */
    DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData);

    /**
     * Defines the method that will throw an error to the view given a failure
     *
     * @param errorMessage the error message to be passed to the view
     * @return the output data to be passed to the view
     */
    DeclareWinnerOD presentError(String errorMessage);
}
