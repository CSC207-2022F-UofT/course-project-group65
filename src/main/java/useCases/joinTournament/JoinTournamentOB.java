package useCases.joinTournament;

public interface JoinTournamentOB {
    JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData);

    JoinTournamentOD prepareFailView(String error);
}
