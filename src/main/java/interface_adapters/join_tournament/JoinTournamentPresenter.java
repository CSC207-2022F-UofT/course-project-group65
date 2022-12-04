package interface_adapters.join_tournament;

import interface_adapters.NextScreenData;
import use_cases.join_tournament.JoinTournamentOB;
import use_cases.join_tournament.JoinTournamentOD;

public class JoinTournamentPresenter implements JoinTournamentOB {

    private NextScreenData nextScreenData;
    @Override
    public JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData) {
        nextScreenData.setCurrentUser(outputData.getUsername());
        nextScreenData.setCurrentBracketID(outputData.getTournamentID());
        return outputData;
    }

    public void setNextScreenData(NextScreenData nextScreenData){
        this.nextScreenData = nextScreenData;
    }

    @Override
    public JoinTournamentOD prepareFailView(String error) {
        throw new JoinTournamentFailed(error);
    }
}
