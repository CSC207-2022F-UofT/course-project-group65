package screens.create_account;

import database.CreateAccount.CreateAccountFileWriter;
import use_cases.create_account.*;
import use_cases.general_classes.InformationRecord;

public class CreateAccountController {
    private CreateAccountIB createAccountIB;

//    public CreateAccountController(CreateAccountIB createAccountIB) {
//        this.createAccountIB = createAccountIB;
//    }
    public CreateAccountController(InformationRecord informationRecord) {
        //this.createAccountIB = createAccountIB;
        CreateAccountOB createAccountOB = new CreateAccountPresenter();
        CreateAccountGateway gateway = new CreateAccountFileWriter("accounts.txt");
        this.createAccountIB = new CreateAccountUC(createAccountOB, informationRecord, gateway);

    }

    public CreateAccountOD create(String username, String password) {
        CreateAccountID createAccountID = new CreateAccountID(username, password);
        return createAccountIB.create(createAccountID);
    };
}
