package useCases.CreateAccount;

import screens.createAccount.CreateAccountFailed;

public interface CreateAccountOB {
    CreateAccountOD prepareSuccessView(CreateAccountOD user);

    CreateAccountOD prepareFailView(String error);
}
