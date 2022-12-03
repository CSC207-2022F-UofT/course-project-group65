package use_cases.change_points;

/** The input boundary for change points. */
public interface ChangePointsIB {

    /** Defines the method to be called to activate use case.
     * @param changePointsID The input data for the use case.
     * @return The output data for the use case.
     */
    ChangePointsOD changePoints(ChangePointsID changePointsID);

}
