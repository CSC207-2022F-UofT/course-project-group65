package useCases.joinTeam;

/**
 An interface for the output boundary of the join team use case.
 */
public interface JoinTeamOB {
    JoinTeamOD SuccessView(JoinTeamOD teamData);
    JoinTeamOD FailView(String error);
}
