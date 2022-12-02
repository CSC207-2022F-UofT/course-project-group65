package screens.log_in;

import use_cases.log_in.LogInOB;
import use_cases.log_in.LogInOD;

public class LogInPresenter implements LogInOB {
    @Override
    public LogInOD prepareSuccessView(LogInOD user) {
        return user;
    }

    @Override
    public LogInOD prepareFailView(String error) {
        throw new LogInFailed(error);
    }
}
