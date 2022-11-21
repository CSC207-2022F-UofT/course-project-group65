package screens.createAccount;


import useCases.CreateAccount.CreateAccountOB;
import useCases.CreateAccount.CreateAccountOD;

public class CreateAccountPresenter implements CreateAccountOB {

    @Override
    public CreateAccountOD prepareSuccessView(CreateAccountOD user) {
        return user;
    }

    @Override
    public CreateAccountOD prepareFailView(String error) {
        throw new CreateAccountFailed(error);
    }
}
