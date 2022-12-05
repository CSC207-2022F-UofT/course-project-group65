package interface_adapters.create_account;

import interface_adapters.data_interface_adapters.create_account_data.CreateAccountFileWriter;
import use_cases.create_account.*;
import use_cases.general_classes.InformationRecord;

/**
 * A class for the Controller of the create account use case.
 */
public class CreateAccountController {
    private final CreateAccountIB createAccountIB;

    /**
     * Creates a new CreateAccountController object.
     *
     * @param informationRecord The class that stores both the BracketRepo and AccountRepo
     */
    public CreateAccountController(InformationRecord informationRecord) {
        //this.createAccountIB = createAccountIB;
        CreateAccountOB createAccountOB = new CreateAccountPresenter();
        CreateAccountGateway gateway = new CreateAccountFileWriter("accounts.txt");
        this.createAccountIB = new CreateAccountUC(createAccountOB, informationRecord, gateway);

    }

    /**
     * Creates an account given an username and a password.
     *
     * @param username the string username
     * @param password the string password
     * @return the create account output data
     */
    public CreateAccountOD create(String username, String password) {
        CreateAccountID createAccountID = new CreateAccountID(username, password);
        return createAccountIB.create(createAccountID);
    }
}
