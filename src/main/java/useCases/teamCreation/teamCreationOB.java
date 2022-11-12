package useCases.teamCreation;

public interface teamCreationOB {
    teamCreationOD prepareSuccessView(teamCreationOD team);

    teamCreationOD prepareFailView(String error);
}
