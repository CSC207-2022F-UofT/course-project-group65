package useCases.teamCreation;

/**
 * the output boundary in teamCreation use case
 */
public interface teamCreationOB {
    /**
     * Prepares the success view if the team was successfully created
     * @param teamData - output data from the usecase
     * @return output data
     */
    teamCreationOD prepareSuccessView(teamCreationOD teamData);
    /**
     * Prepares the success view if the team was successfully created
     * @param error - error message that indicates why the team cannot be created
     * @return output data
     */
    teamCreationOD prepareFailView(String error);
}
