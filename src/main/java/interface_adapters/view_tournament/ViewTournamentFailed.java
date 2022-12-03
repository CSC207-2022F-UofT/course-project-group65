package interface_adapters.view_tournament;

public class ViewTournamentFailed extends RuntimeException{
    public ViewTournamentFailed(String error) {
        super(error) ;
    }
}
