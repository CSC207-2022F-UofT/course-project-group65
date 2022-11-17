package screens.createBracket;

import useCases.createBracket.CreateBracketOB;
import useCases.createBracket.CreateBracketOD;

public class CreateBracketPresenter implements CreateBracketOB {
    /*
    A class for the Presenter of the create bracket use case.
     */

    public CreateBracketOD presentSuccess(CreateBracketOD viewData) {
        return viewData;
    }

    public CreateBracketOD presentError(String error) {
        throw new CreateBracketFailed(error);
    }
}
