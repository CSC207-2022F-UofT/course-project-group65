package use_cases.create_account;

import entities.*;
import use_cases.general_classes.InformationRecord;

import java.util.ArrayList;
import java.util.HashMap;
/**
 * This is the Use Case (interactor) class for the CreateAccount use case. This class is responsible
 * for creating a new account and updating the account repository.
 * It connects and uses many of the classes used in this package.
 * Implements the CreateAccountIB to allow the controller to call the create method.
 */
public class CreateAccountUC implements CreateAccountIB {
    final CreateAccountOB userCreateAccountOB;
    final AccountRepo data;
    private final CreateAccountGateway gateway;

    public CreateAccountUC(CreateAccountOB userCreateAccountOB, InformationRecord informationRecord, CreateAccountGateway gateway) {
        this.userCreateAccountOB = userCreateAccountOB;
        this.data = informationRecord.getAccountData();
        this.gateway = gateway;
    }

    public boolean usernameExists(CreateAccountID requestModel, AccountRepo data) {
        return (data.getAllUsernames().contains(requestModel.getUsername()));
    }


    @Override
    public CreateAccountOD create(CreateAccountID requestModel) {
        if (usernameExists(requestModel, data)) {
            return userCreateAccountOB.prepareFailView("User already exists.");
        }

        UserFactory userFactory = new DefaultUserFactory();

        User user = userFactory.create(requestModel.getUsername(), requestModel.getPassword(),
                new HashMap<>(), 0, new ArrayList<>());

        data.addUser(user);

        CreateAccountDSID dataStoreID = new CreateAccountDSID(data);

        try {
            this.gateway.save(dataStoreID);
        } catch (Exception e){
            return userCreateAccountOB.prepareFailView("Error saving to database.");
        }

        CreateAccountOD accountOD = new CreateAccountOD(user.getUsername(), user.getPassword());
        return userCreateAccountOB.prepareSuccessView(accountOD);
    }
}
