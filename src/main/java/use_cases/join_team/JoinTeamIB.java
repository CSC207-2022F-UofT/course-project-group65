package use_cases.join_team;

/**
 * An interface for the input boundary of the join team use case.
 */
public interface JoinTeamIB {
    /**
     * Join the  team
     *
     * @param joinID - the input boundary of joinTeam.
     */
    JoinTeamOD joinTeam(JoinTeamID joinID);

}
