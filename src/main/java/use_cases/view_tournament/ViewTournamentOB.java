package use_cases.view_tournament;

public interface ViewTournamentOB {
    ViewTournamentOD prepareSuccessView(ViewTournamentOD outputData);

    ViewTournamentOD prepareFailView(String error);
}
