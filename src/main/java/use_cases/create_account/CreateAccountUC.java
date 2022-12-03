package use_cases.create_account;

import entities.*;
import use_cases.general_classes.InformationRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateAccountUC implements CreateAccountIB {
    final CreateAccountOB userCreateAccountOB;
    final AccountRepo data;
    final BracketRepo bracketData;
    private final CreateAccountGateway gateway;

    public CreateAccountUC(CreateAccountOB userCreateAccountOB, InformationRecord informationRecord, CreateAccountGateway gateway) {
        this.userCreateAccountOB = userCreateAccountOB;
        this.bracketData = informationRecord.getBracketData();
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
