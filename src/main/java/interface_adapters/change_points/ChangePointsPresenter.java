package interface_adapters.change_points;
import interface_adapters.view_interfaces.bracket_operation_interface.ChangePointsBOView;
import interface_adapters.view_interfaces.main_view_interfaces.ChangePointsExtendedView;
import use_cases.change_points.*;

/** The presenter for the change points use case which is responsible for
 *  formatting the output of the use case for the user interface.
 */
public class ChangePointsPresenter implements ChangePointsOB {

    private ChangePointsExtendedView extendedView;
    private ChangePointsBOView view;

    public ChangePointsPresenter(ChangePointsExtendedView extendedView) {
        this.extendedView = extendedView;
    }

    /** The output of the use case.
     * @param outputData The output of the use case.
     * @return The output of the use case.
     */
    public ChangePointsOD presentSuccess(ChangePointsOD outputData){
        extendedView.updateGameScore(outputData.getGame(), outputData.getTeams(), outputData.getPoints());
        view.changeTeamsLabel(outputData.getTeams().get(0), outputData.getTeams().get(1), outputData.getPoints().get(0), outputData.getPoints().get(1));
        return outputData;
    }

    public void setView(ChangePointsBOView view) {
        this.view = view;
    }

    /** Returns an error message if the use case fails.
     * @param errorMessage The error
     * @return The output of the use case.
     */
    public ChangePointsOD presentError(String errorMessage){
        throw new ChangePointsFailed(errorMessage);
    }
}
