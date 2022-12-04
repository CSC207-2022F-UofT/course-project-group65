package interface_adapters.log_in;

import interface_adapters.NextScreenData;
import use_cases.log_in.*;
import use_cases.general_classes.InformationRecord;

public class LogInController {
    private final LogInIB logInIB;
    private LogInPresenter logInPresenter;

    public LogInController(InformationRecord informationRecord) {
        this.logInPresenter = new LogInPresenter();
        this.logInIB = new LogInUC(this.logInPresenter, informationRecord);
    }

    public void setPresenterData(NextScreenData nextScreenData){
        this.logInPresenter.setNextScreenData(nextScreenData);
    }

    public LogInOD login(String username, String password) {
        LogInID logInID = new LogInID(username, password);

        return logInIB.logIn(logInID);
    }
}
