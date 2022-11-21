package screens.logIn;

import useCases.LogIn.LogInOB;
import useCases.LogIn.LogInOD;

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
