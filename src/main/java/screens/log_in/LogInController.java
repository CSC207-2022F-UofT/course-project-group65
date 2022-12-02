package screens.log_in;

import use_cases.log_in.*;
import use_cases.general_classes.InformationRecord;

public class LogInController {
    private final LogInIB logInIB;

//    public LogInController(LogInIB logInIB) {
//        this.logInIB = logInIB;
//    }

    public LogInController(InformationRecord informationRecord) {
        LogInOB logInOB = new LogInPresenter();
        this.logInIB = new LogInUC(logInOB, informationRecord);
    }

    public LogInOD login(String username, String password) {
        LogInID logInID = new LogInID(username, password);

        return logInIB.logIn(logInID);
    }
}
