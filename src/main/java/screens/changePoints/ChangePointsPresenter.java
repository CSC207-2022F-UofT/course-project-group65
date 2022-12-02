package screens.changePoints;
import use_cases.change_points.*;

public class ChangePointsPresenter implements ChangePointsOB {

    public ChangePointsOD presentSuccess(ChangePointsOD outputData){
        return outputData;
    }

    public ChangePointsOD presentError(String errorMessage){
        throw new ChangePointsFailed(errorMessage);
    }
}
