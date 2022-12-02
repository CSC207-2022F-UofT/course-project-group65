package use_cases.join_team;

/**
 An interface for the input boundary of the join team use case.
 */
public interface JoinTeamIB {
    JoinTeamOD joinTeam(JoinTeamID joinID);

}
