package use_cases.change_points;

public interface ChangePointsOB {
    ChangePointsOD presentSuccess(ChangePointsOD outputData);
    ChangePointsOD presentError(String errorMessage);
}

