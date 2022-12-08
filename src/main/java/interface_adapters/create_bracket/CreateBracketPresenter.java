package interface_adapters.create_bracket;

import interface_adapters.NextScreenData;
import use_cases.create_bracket.CreateBracketOB;
import use_cases.create_bracket.CreateBracketOD;

public class CreateBracketPresenter implements CreateBracketOB {
    /*
    A class for the Presenter of the create bracket use case.
     */
    private NextScreenData nextScreenData;

    public CreateBracketOD presentSuccess(CreateBracketOD viewData) {
        nextScreenData.setCurrentBracketID(viewData.getBracketID());
        nextScreenData.setCurrentUser(viewData.getUsername());
        return viewData;
    }

    public void setNextScreenData(NextScreenData nextScreenData) {
        this.nextScreenData = nextScreenData;
    }
    public CreateBracketOD presentError(String error) {
        throw new CreateBracketFailed(error);
    }
}
