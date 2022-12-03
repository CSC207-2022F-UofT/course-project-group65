package use_cases.view_tournament;

public class ViewTournamentID {
    private final int TOURNAMENT_ID;

    public ViewTournamentID(int tournamentID){
        TOURNAMENT_ID = tournamentID;
    }
    int getTournamentID() {
        return TOURNAMENT_ID;
    }
}
