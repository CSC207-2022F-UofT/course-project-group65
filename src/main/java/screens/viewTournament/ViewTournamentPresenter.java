package screens.viewTournament;

import useCases.viewTournament.ViewTournamentOB;
import useCases.viewTournament.ViewTournamentOD;

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
