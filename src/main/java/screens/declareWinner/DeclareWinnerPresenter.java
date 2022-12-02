package screens.declareWinner;
import use_cases.declare_winner.*;

public class DeclareWinnerPresenter implements DeclareWinnerOB {

    // The success or faliure is shown to the view, completing the process.
    public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData){
        return outputData;
    }

    public DeclareWinnerOD presentError(String errorMessage){
        throw new DeclareWinnerFailed(errorMessage); //
    }
}

