package use_cases.advance_team;

public interface AdvanceTeamOB {

    AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData);

    AdvanceTeamOD presentError(String errorMessage);
}
