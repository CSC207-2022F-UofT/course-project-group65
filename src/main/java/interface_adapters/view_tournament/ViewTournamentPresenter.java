package interface_adapters.view_tournament;

import interface_adapters.NextScreenData;
import use_cases.view_tournament.ViewTournamentOB;
import use_cases.view_tournament.ViewTournamentOD;

public class ViewTournamentPresenter implements ViewTournamentOB {

    private NextScreenData nextScreenData;
    @Override
    public ViewTournamentOD prepareSuccessView(ViewTournamentOD outputData) {
        nextScreenData.setCurrentUser(outputData.getUsername());
        nextScreenData.setCurrentBracketID(outputData.getTournamentID());
        return outputData;
    }

    public void setNextScreenData(NextScreenData nextScreenData) {
        this.nextScreenData = nextScreenData;
    }

    @Override
    public ViewTournamentOD prepareFailView(String error) {
        throw new ViewTournamentFailed(error);
    }
}
