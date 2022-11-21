package useCases.CreateAccount;

import entities.AccountRepo;
import entities.BracketRepo;
import entities.User;
import entities.UserFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateAccountUC implements CreateAccountIB {
    final CreateAccountOB userCreateAccountOB;
    final UserFactory userFactory;
    final AccountRepo data;
    final BracketRepo bracketData;

    public CreateAccountUC(CreateAccountOB userCreateAccountOB, UserFactory userFactory, AccountRepo data, BracketRepo bracketData) {
        this.userFactory = userFactory;
        this.userCreateAccountOB = userCreateAccountOB;
        this.data = data;
        this.bracketData = bracketData;
    }

    public boolean usernameExists(CreateAccountID requestModel, AccountRepo data) {
        return (data.getAllUsernames().contains(requestModel.getUsername()));
    }


    @Override
    public CreateAccountOD create(CreateAccountID requestModel) {
        if (usernameExists(requestModel, data)) {
            return userCreateAccountOB.prepareFailView("User already exists.");
        }

        User user = userFactory.create(requestModel.getUsername(), requestModel.getPassword(),
                new HashMap<Integer, String>(), 0, new ArrayList<Integer>());

        data.addUser(user);

        CreateAccountOD accountOD = new CreateAccountOD(user.getUsername(), user.getPassword());
        return userCreateAccountOB.prepareSuccessView(accountOD);
    }
}
