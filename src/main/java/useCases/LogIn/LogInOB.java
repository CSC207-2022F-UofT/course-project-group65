package useCases.LogIn;


public interface LogInOB {
    LogInOD prepareSuccessView(LogInOD user);

    LogInOD prepareFailView(String error);
}
