package use_cases.join_tournament;

public class JoinTournamentID {
    private final String tournamentID;

    public JoinTournamentID(String invite) {
        tournamentID = invite;
    }

    String getInvite() {
        return tournamentID;
    }
}
