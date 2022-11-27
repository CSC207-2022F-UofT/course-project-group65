package screens.logIn;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.LogIn.*;

public class LogInController {
    private final LogInIB logInIB;

//    public LogInController(LogInIB logInIB) {
//        this.logInIB = logInIB;
//    }

    public LogInController(Object accountDatabase, Object bracketDatabase) {
        LogInOB logInOB = new LogInPresenter();
        this.logInIB = new LogInUC(logInOB, accountDatabase, bracketDatabase);
    }

    public LogInOD login(String username, String password) {
        LogInID logInID = new LogInID(username, password);

        return logInIB.logIn(logInID);
    }
}
