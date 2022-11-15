package useCases.LogIn;

import entities.AccountRepo;
import entities.User;

import java.util.Objects;

public class LogInUC implements LogInIB{

    public enum LoginResult {
        SUCCESS, FAILURE // Should we do NO_SUCH_USER as well as SUCCESS and FAILURE?
    }

    public boolean usernameExists(LogInID requestModel, AccountRepo data) {
        return (data.getAllUsernames().contains(requestModel.getUsername()));
    }

    public boolean passwordMatch(String username, String password, AccountRepo data) {
        return (Objects.equals(data.getUser(username).getPassword(), password));
    }


    @Override
    public LoginResult logIn(LogInID requestModel, String username, String password, AccountRepo data) {
        if (usernameExists(requestModel, data) && passwordMatch(username, password, data)) {
            return LoginResult.SUCCESS;
        } else {
            return LoginResult.FAILURE;
        }
    }
}
