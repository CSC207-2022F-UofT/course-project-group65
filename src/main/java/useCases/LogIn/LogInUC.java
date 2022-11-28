package useCases.LogIn;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.DefaultUser;
import entities.User;

import java.util.HashMap;
import java.util.Objects;

public class LogInUC implements LogInIB{
    final LogInOB userLogInOB;
    final AccountRepo data;
    final BracketRepo bracketData;

//    public LogInUC(LogInOB userLogInOB, AccountRepo data, BracketRepo bracketData) {
//        this.userLogInOB = userLogInOB;
//        this.data = data;
//        this.bracketData = bracketData;
//    }

    public LogInUC(LogInOB userLogInOB, Object accountDatabase, Object bracketDatabase) {
        this.userLogInOB = userLogInOB;

        if (accountDatabase == null || !accountDatabase.getClass().getName().equals("entities.AccountRepo")) {
            this.data = new AccountRepo();
        } else {
            this.data = (AccountRepo) accountDatabase;
        }

        if (bracketDatabase == null || !bracketDatabase.getClass().getName().equals("entities.BracketRepo")) {
            this.bracketData = new BracketRepo();
        } else {
            this.bracketData = (BracketRepo) bracketDatabase;
        }

//        this.data = data;
//        this.bracketData = bracketData;
    }

    public boolean usernameExists(LogInID requestModel, AccountRepo data) {
        return (data.getAllUsernames().contains(requestModel.getUsername()));
    }

    public boolean passwordMatch(String username, String password, AccountRepo data) {
        return (Objects.equals(data.getUser(username).getPassword(), password));
    }


    @Override
    public LogInOD logIn(LogInID requestModel) {
        if (usernameExists(requestModel, data) && passwordMatch(requestModel.getUsername(), requestModel.getPassword(), data)) {
            User currentUser = data.getUser(requestModel.getUsername());
            return userLogInOB.prepareSuccessView(new LogInOD(requestModel.getUsername(), data, bracketData));

        } else {
            return userLogInOB.prepareFailView("username and/or password is incorrect");
        }
    }
}
