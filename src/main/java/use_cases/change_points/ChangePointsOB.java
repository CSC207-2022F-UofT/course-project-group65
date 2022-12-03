package use_cases.change_points;

/** The output boundary for the change points use case. */
public interface ChangePointsOB {

    /** Defines the method to present a success to the user.
     * @param outputData the output data.
     * @return the output data. */

    ChangePointsOD presentSuccess(ChangePointsOD outputData);

    /** Defines the method to present a failure to the user.
     * @param errorMessage the error message.
     * @return the output data. */
    ChangePointsOD presentError(String errorMessage);
}

