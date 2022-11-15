package screens.declareWinner;
import useCases.declareWinner.DeclareWinnerOB;
import useCases.declareWinner.DeclareWinnerOD;

public class DeclareWinnerPresenter implements DeclareWinnerOB {

    public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData){
        return outputData;
    }

    public DeclareWinnerOD presentError(String errorMessage){
        throw new DeclareWinnerFailed(errorMessage);
    }
}

