package interface_adapters.declare_winner;
import use_cases.declare_winner.*;

/** The presenter for the declare winner use case, which is responsible for
 *  formatting the output of the use case for the user interface. */
public class DeclareWinnerPresenter implements DeclareWinnerOB {

    /** The output which is presented to the view.
     * @param outputData the output data to be passed to the view
     * @return the output to be passed to the view */
    public DeclareWinnerOD presentSuccess(DeclareWinnerOD outputData){
        return outputData;
    }

    /** To be called when there is an error in the use case.
     * @param errorMessage the error message to be passed to the view
     * @return the output to be passed to the view */
    public DeclareWinnerOD presentError(String errorMessage){
        throw new DeclareWinnerFailed(errorMessage);
    }
}

