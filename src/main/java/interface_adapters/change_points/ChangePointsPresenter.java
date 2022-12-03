package interface_adapters.change_points;
import use_cases.change_points.*;

/** The presenter for the change points use case which is responsible for
 *  formatting the output of the use case for the user interface.
 */
public class ChangePointsPresenter implements ChangePointsOB {

    /** The output of the use case.
     * @param outputData The output of the use case.
     * @return The output of the use case.
     */
    public ChangePointsOD presentSuccess(ChangePointsOD outputData){
        return outputData;
    }

    /** Returns an error message if the use case fails.
     * @param errorMessage The error
     * @return The output of the use case.
     */
    public ChangePointsOD presentError(String errorMessage){
        throw new ChangePointsFailed(errorMessage);
    }
}
