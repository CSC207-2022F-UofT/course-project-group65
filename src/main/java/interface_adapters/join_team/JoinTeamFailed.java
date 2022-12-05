package interface_adapters.join_team;

/**
 * A class for the output data of the join team use case.
 */
public class JoinTeamFailed extends RuntimeException {
    public JoinTeamFailed(String error) {
        super(error);
    }

}
