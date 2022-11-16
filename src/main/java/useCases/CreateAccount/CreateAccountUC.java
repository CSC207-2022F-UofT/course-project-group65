package useCases.CreateAccount;

import entities.AccountRepo;
import entities.User;
import entities.UserFactory;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateAccountUC implements CreateAccountIB {
    final CreateAccountOB userCreateAccountOB;
    final UserFactory userFactory;

    public CreateAccountUC(CreateAccountOB userCreateAccountOB, UserFactory userFactory) {
        this.userFactory = userFactory;
        this.userCreateAccountOB = userCreateAccountOB;
    }

    public boolean usernameExists(CreateAccountID requestModel, AccountRepo data) {
        return (data.getAllUsernames().contains(requestModel.getUsername()));
    }


    @Override
    public CreateAccountOD create(CreateAccountID requestModel, AccountRepo data) {
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
