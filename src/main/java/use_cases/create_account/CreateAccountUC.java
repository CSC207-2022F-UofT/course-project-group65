package use_cases.create_account;

import entities.*;
import use_cases.general_classes.InformationRecord;

import java.util.ArrayList;
import java.util.HashMap;

public class CreateAccountUC implements CreateAccountIB {
    final CreateAccountOB userCreateAccountOB;
//    final UserFactory userFactory;
    final AccountRepo data;
    final BracketRepo bracketData;
    private CreateAccountGateway gateway;

//    public CreateAccountUC(CreateAccountOB userCreateAccountOB, AccountRepo data, BracketRepo bracketData,
//                           CreateAccountGateway gateway) {
//        this.userCreateAccountOB = userCreateAccountOB;
//        this.data = data;
//        this.bracketData = bracketData;
//        this.gateway = gateway;
//
////        this.userFactory = userFactory;
//    }

    public CreateAccountUC(CreateAccountOB userCreateAccountOB, InformationRecord informationRecord, CreateAccountGateway gateway) {
        this.userCreateAccountOB = userCreateAccountOB;
        this.bracketData = informationRecord.getBracketData();
        this.data = informationRecord.getAccountData();


//        if (accountDatabase == null || !accountDatabase.getClass().getName().equals("entities.AccountRepo")) {
//            this.data = new AccountRepo();
//        } else {
//            this.data = (AccountRepo) accountDatabase;
//        }
//        if (bracketDatabase == null || !bracketDatabase.getClass().getName().equals("entities.BracketRepo")) {
//            this.bracketData = new BracketRepo();
//        } else {
//            this.bracketData = (BracketRepo) bracketDatabase;
//        }
        this.gateway = gateway;
    }

//    public CreateAccountUC(CreateAccountOB userCreateAccountOB, UserFactory userFactory, AccountRepo data, BracketRepo bracketData) {
//        this.userFactory = userFactory;
//        this.userCreateAccountOB = userCreateAccountOB;
//        this.data = data;
//        this.bracketData = bracketData;
//    }
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
                new HashMap<Integer, String>(), 0, new ArrayList<Integer>());

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
