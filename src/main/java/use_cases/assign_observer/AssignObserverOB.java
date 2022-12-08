package use_cases.assign_observer;

/**
 * The output boundary for the assign observer use case.
 */
public interface AssignObserverOB {
    /**
     * Defines the method to present a success to the user.
     *
     * @param outputData the output data.
     * @return the output data.
     */
    AssignObserverOD prepareSuccessView(AssignObserverOD outputData);

    /**
     * Defines the method to present a failure to the user.
     *
     * @param error the error message.
     * @return the output data.
     */
    AssignObserverOD prepareFailView(String error);
}
