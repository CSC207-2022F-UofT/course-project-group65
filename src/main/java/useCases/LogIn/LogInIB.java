package useCases.LogIn;

import entities.AccountRepo;
import entities.BracketRepo;

public interface LogInIB {
    LogInUC.LoginResult logIn(LogInID requestModel, String username, String password, AccountRepo data, BracketRepo bracketData);
}
