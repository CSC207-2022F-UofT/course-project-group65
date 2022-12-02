package use_cases.join_tournament;

public interface JoinTournamentOB {
    JoinTournamentOD prepareSuccessView(JoinTournamentOD outputData);

    JoinTournamentOD prepareFailView(String error);
}
