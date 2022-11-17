package useCases.teamCreation;

public interface teamCreationOB {
    teamCreationOD prepareSuccessView(teamCreationOD teamData);

    teamCreationOD prepareFailView(String error);
}
