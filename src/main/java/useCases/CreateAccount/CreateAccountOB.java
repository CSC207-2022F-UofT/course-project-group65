package useCases.CreateAccount;

public interface CreateAccountOB {
    CreateAccountOD prepareSuccessView(CreateAccountOD user);

    CreateAccountOD prepareFailView(String error);
}
