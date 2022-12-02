package screens.logIn;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.LogIn.*;
import useCases.generalClasses.InformationRecord;

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
