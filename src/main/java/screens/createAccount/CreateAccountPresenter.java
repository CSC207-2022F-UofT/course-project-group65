package screens.createAccount;


import use_cases.create_account.CreateAccountOB;
import use_cases.create_account.CreateAccountOD;

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
