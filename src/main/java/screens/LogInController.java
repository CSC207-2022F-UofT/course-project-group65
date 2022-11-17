package screens;

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

    public void runLogin(LogInID requestModel, String username, String password, AccountRepo data, BracketRepo bracketData) {
        // Note: hands off the work to the use case class.
        LogInOD result = logInIB.logIn(requestModel, username, password, data, bracketData);

    }
}
