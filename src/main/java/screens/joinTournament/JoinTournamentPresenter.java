package screens.joinTournament;

import use_cases.join_tournament.JoinTournamentOB;
import use_cases.join_tournament.JoinTournamentOD;

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
