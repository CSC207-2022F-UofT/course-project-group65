package interface_adapters.advance_team;

public class AdvanceTeamFailed extends RuntimeException {
    public AdvanceTeamFailed(String errorMessage) {
        super(errorMessage);
    }
}

