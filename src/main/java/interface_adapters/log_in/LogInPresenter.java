package interface_adapters.log_in;

import interface_adapters.NextScreenData;
import use_cases.log_in.LogInOB;
import use_cases.log_in.LogInOD;

public class LogInPresenter implements LogInOB {

    NextScreenData nextScreenData;
    @Override
    public LogInOD prepareSuccessView(LogInOD user) {
        nextScreenData.setCurrentUser(user.getUsername());
        return user;
    }

    public void setNextScreenData(NextScreenData nextScreenData){
        this.nextScreenData = nextScreenData;
    }

    @Override
    public LogInOD prepareFailView(String error) {
        throw new LogInFailed(error);
    }
}
