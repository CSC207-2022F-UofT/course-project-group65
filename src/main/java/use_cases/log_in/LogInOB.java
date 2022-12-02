package use_cases.log_in;


public interface LogInOB {
    LogInOD prepareSuccessView(LogInOD user);

    LogInOD prepareFailView(String error);
}
