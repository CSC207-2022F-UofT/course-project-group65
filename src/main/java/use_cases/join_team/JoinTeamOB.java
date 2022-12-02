package use_cases.join_team;

/**
 An interface for the output boundary of the join team use case.
 */
public interface JoinTeamOB {
    /**
     * Prepares the success view if the player successfully joined the team
     * @param teamData - output data from the usecase
     * @return output data
     */
    JoinTeamOD SuccessView(JoinTeamOD teamData);
    /**
     * Prepares the success view if the player failed to join the team
     * @param error - error message that indicates why the player cannot join the team
     * @return output data
     */
    JoinTeamOD FailView(String error);
}
