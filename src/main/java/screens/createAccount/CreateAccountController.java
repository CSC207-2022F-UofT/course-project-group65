package screens.createAccount;

import entities.AccountRepo;
import useCases.CreateAccount.CreateAccountIB;
import useCases.CreateAccount.CreateAccountID;
import useCases.CreateAccount.CreateAccountOD;

public class CreateAccountController {
    private CreateAccountIB createAccountIB;

    public CreateAccountController(CreateAccountIB createAccountIB) {
        this.createAccountIB = createAccountIB;
    }

    public CreateAccountOD create(String username, String password) {
        CreateAccountID createAccountID = new CreateAccountID(username, password);
        return createAccountIB.create(createAccountID);
    };
}
