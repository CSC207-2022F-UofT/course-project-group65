package useCases.joinTeam;

/**
 An interface for the input boundary of the join team use case.
 */
public interface JoinTeamIB {
    JoinTeamOD joinTeam(JoinTeamID joinID);

}
