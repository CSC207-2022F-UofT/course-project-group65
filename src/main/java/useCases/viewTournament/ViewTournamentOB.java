package useCases.viewTournament;

public interface ViewTournamentOB {
    ViewTournamentOD prepareSuccessView(ViewTournamentOD outputData);

    ViewTournamentOD prepareFailView(String error);
}
