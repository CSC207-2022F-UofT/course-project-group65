package screens.create_bracket;

import use_cases.create_bracket.CreateBracketOB;
import use_cases.create_bracket.CreateBracketOD;

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
