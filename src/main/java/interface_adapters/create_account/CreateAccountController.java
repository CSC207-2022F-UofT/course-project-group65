package interface_adapters.create_account;

import interface_adapters.data_interface_adapters.create_account_data.CreateAccountFileWriter;
import use_cases.create_account.*;
import use_cases.general_classes.InformationRecord;

public class CreateAccountController {
    private final CreateAccountIB createAccountIB;

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
