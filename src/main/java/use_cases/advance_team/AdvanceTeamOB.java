package use_cases.advance_team;

/** The output boundary for the AdvanceTeam use case. */
public interface AdvanceTeamOB {

    /**
     * The method defined to pass on the information to the view.
     * @param outputData The output data to be passed on to the view
     * @return The output data to be passed on to the view
     */
    AdvanceTeamOD presentSuccess(AdvanceTeamOD outputData);

    /**
     * The method defined to present an error.
     * @param errorMessage The error message to be presented to the user
     * @return The error message to be passed on to the view
     */
    AdvanceTeamOD presentError(String errorMessage);
}
