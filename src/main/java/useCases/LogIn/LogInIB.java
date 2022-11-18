package useCases.LogIn;

import entities.AccountRepo;
import entities.BracketRepo;

public interface LogInIB {
    LogInOD logIn(LogInID requestModel);
}
