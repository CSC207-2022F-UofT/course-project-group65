package use_cases.join_tournament;

public class JoinTournamentID {
    private final String TOURNAMENT_ID;

    public JoinTournamentID(String invite) {
        TOURNAMENT_ID = invite;
    }

    String getInvite() {
        return TOURNAMENT_ID;
    }
}
