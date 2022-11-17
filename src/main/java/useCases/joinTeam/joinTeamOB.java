package useCases.joinTeam;

public interface joinTeamOB {
    joinTeamOD SuccessView(joinTeamOD teamData);
    joinTeamOD FailView(String error);
}
