package useCases.LogIn;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.DefaultUser;
import entities.User;

import java.util.HashMap;
import java.util.Objects;

public class LogInUC implements LogInIB{
    final LogInOB userLogInOB;
    final String username;
    final AccountRepo data;
    final BracketRepo bracketData;

    public LogInUC(LogInOB userLogInOB, String username, AccountRepo data, BracketRepo bracketData) {
        this.userLogInOB = userLogInOB;
        this.username = username;
        this.data = data;
        this.bracketData = bracketData;
    }

    public boolean usernameExists(LogInID requestModel, AccountRepo data) {
        return (data.getAllUsernames().contains(requestModel.getUsername()));
    }

    public boolean passwordMatch(String username, String password, AccountRepo data) {
        return (Objects.equals(data.getUser(username).getPassword(), password));
    }


    @Override
    public LogInOD logIn(LogInID requestModel, String username, String password, AccountRepo data, BracketRepo bracketData) {
        if (usernameExists(requestModel, data) && passwordMatch(username, password, data)) {
            User currentUser = data.getUser(username);
            return userLogInOB.prepareSuccessView(new LogInOD(username, new HashMap<Integer, String>(), 0));

        } else {
            return userLogInOB.prepareFailView("username and/or password is incorrect");
        }
    }
}
