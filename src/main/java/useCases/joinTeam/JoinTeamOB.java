package useCases.joinTeam;

public interface JoinTeamOB {
    JoinTeamOD SuccessView(JoinTeamOD teamData);
    JoinTeamOD FailView(String error);
}
