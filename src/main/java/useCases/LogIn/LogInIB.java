package useCases.LogIn;

import entities.AccountRepo;

public interface LogInIB {
    LogInUC.LoginResult logIn(LogInID requestModel, String username, String password, AccountRepo data);
}
