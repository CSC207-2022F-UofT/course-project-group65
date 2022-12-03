package useCases.joinTournament;

public class JoinTournamentID {
    private String invite;

    public JoinTournamentID(String invite) {
        this.invite = invite;
    }

    String getInvite() {
        return invite;
    }
}