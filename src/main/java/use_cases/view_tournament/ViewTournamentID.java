package use_cases.view_tournament;

public class ViewTournamentID {
    private int tournamentID;

    public ViewTournamentID(int tournamentID){
        this.tournamentID = tournamentID;
    }
    int getTournamentID() {
        return tournamentID;
    }

    void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

}
