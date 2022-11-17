package useCases.advanceTeam;

public interface AdvanceTeamOB {

    AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData);

    AdvanceTeamOD presentError(String errorMessage);
}
