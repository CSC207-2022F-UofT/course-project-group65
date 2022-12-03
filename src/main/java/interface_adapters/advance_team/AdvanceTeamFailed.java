package interface_adapters.advance_team;

/** Exception defined for failure to advance team. */
public class AdvanceTeamFailed extends RuntimeException {
    public AdvanceTeamFailed(String errorMessage) {
        super(errorMessage);
    }
}

