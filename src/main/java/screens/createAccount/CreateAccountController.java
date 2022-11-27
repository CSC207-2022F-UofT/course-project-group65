package screens.createAccount;

import database.CreateAccount.CreateAccountFileWriter;
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
        CreateAccountGateway gateway = new CreateAccountFileWriter("accounts.txt");
        this.createAccountIB = new CreateAccountUC(createAccountOB, accountDatabase, bracketDatabase, gateway);

    }

    public CreateAccountOD create(String username, String password) {
        CreateAccountID createAccountID = new CreateAccountID(username, password);
        return createAccountIB.create(createAccountID);
    };
}
