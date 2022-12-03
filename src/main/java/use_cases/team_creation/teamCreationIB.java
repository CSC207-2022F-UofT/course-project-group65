package use_cases.team_creation;

/**
 * This is the input boundary interface in teamCreation use case
 */
public interface teamCreationIB {
    /** Creates the new team
     * @param teamID - the input boundary of teamCreation
     */
    teamCreationOD createNewTeam(teamCreationID teamID);
}
