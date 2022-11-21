package screens.changePoints;
import useCases.changePoints.*;

public class ChangePointsPresenter implements ChangePointsOB {

    public ChangePointsOD presentSuccess(ChangePointsOD outputData){
        return outputData;
    }

    public ChangePointsOD presentError(String errorMessage){
        throw new ChangePointsFailed(errorMessage);
    }
}
