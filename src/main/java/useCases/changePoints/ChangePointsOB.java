package useCases.changePoints;

public interface ChangePointsOB {
    ChangePointsOD presentSuccess(ChangePointsOD outputData);
    ChangePointsOD presentError(String errorMessage);
}

