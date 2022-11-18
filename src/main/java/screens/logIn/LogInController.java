package screens.logIn;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.LogIn.LogInIB;
import useCases.LogIn.LogInID;
import useCases.LogIn.LogInOD;
import useCases.LogIn.LogInUC;

public class LogInController {
    private final LogInIB logInIB;

    public LogInController(LogInIB logInIB) {
        this.logInIB = logInIB;
    }

    public LogInOD login(String username, String password) {
        LogInID logInID = new LogInID(username, password);

        return logInIB.logIn(logInID);
    }
}
