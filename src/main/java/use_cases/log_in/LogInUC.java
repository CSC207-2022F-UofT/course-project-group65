package use_cases.log_in;

import entities.AccountRepo;
import use_cases.general_classes.InformationRecord;

import java.util.Objects;

/**
 * This is the Use Case (interactor) class for the LogIn use case. This class is responsible
 * for logging a user in and updating the account repository.
 * It connects and uses many of the classes used in this package.
 * Implements LogInIB to allow the controller to call the login method.
 */

public class LogInUC implements LogInIB{
    private final LogInOB userLogInOB;
    private final AccountRepo data;
    public LogInUC(LogInOB userLogInOB, InformationRecord informationRecord) {
        this.userLogInOB = userLogInOB;
        this.data = informationRecord.getAccountData();
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
            data.getUser(requestModel.getUsername());
            return userLogInOB.prepareSuccessView(new LogInOD(requestModel.getUsername()));

        } else {
            return userLogInOB.prepareFailView("username and/or password is incorrect");
        }
    }
}
