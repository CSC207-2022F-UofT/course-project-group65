package interface_adapters.join_tournament;

public class JoinTournamentFailed extends RuntimeException{
    public JoinTournamentFailed(String error) {
        super(error) ;
    }
}
