package useCases.advanceTeam;

public interface AdvanceTeamOB {

    public AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData);

    public AdvanceTeamOD presentError(String errorMessage);
}
