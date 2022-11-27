package screens.createAccount;

import entities.AccountRepo;
import useCases.CreateAccount.*;

public class CreateAccountController {
    private CreateAccountIB createAccountIB;

//    public CreateAccountController(CreateAccountIB createAccountIB) {
//        this.createAccountIB = createAccountIB;
//    }
    public CreateAccountController(Object accountDatabase, Object bracketDatabase) {
        //this.createAccountIB = createAccountIB;
        CreateAccountOB createAccountOB = new CreateAccountPresenter();
        this.createAccountIB = new CreateAccountUC(createAccountOB, accountDatabase, bracketDatabase);
    }

    public CreateAccountOD create(String username, String password) {
        CreateAccountID createAccountID = new CreateAccountID(username, password);
        return createAccountIB.create(createAccountID);
    };
}
