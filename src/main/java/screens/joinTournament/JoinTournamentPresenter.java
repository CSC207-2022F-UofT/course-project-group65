package screens.joinTournament;

import useCases.joinTournament.JoinTournamentOB;
import useCases.joinTournament.JoinTournamentOD;

public class JoinTournamentPresenter implements JoinTournamentOB {
    @Override
    public JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData) {
        return outputData;
    }

    @Override
    public JoinTournamentOD prepareFailView(String error) {
        throw new JoinTournamentFailed(error);
    }
}
