package screens.viewTournament;

import use_cases.view_tournament.ViewTournamentOB;
import use_cases.view_tournament.ViewTournamentOD;

public class ViewTournamentPresenter implements ViewTournamentOB {
    @Override
    public ViewTournamentOD prepareSuccessView(ViewTournamentOD outputData) {
        return outputData;
    }

    @Override
    public ViewTournamentOD prepareFailView(String error) {
        throw new ViewTournamentFailed(error);
    }
}
