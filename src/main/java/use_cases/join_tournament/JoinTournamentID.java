package use_cases.join_tournament;

public class JoinTournamentID {
    private String invite;

    public JoinTournamentID(String invite) {
        this.invite = invite;
    }

    String getInvite() {
        return invite;
    }

    void setInvite(String invite) {
        this.invite = invite;
    }
}
