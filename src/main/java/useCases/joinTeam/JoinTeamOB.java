package useCases.joinTeam;

public interface JoinTeamOB {
    /*
    An interface for the output boundary of the join team use case.
     */
    JoinTeamOD SuccessView(JoinTeamOD teamData);
    JoinTeamOD FailView(String error);
}
