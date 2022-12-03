package use_cases.advance_team;

/** This is the input boundaray for the AdvanceTeam use case. */
public interface AdvanceTeamIB {

    /** Defines the method that will be called when the use case is executed.
     * @param advanceTeamID The input data.
     * @return The output data
     */
    AdvanceTeamOD advanceWinner(AdvanceTeamID advanceTeamID);
}
