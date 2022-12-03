package use_cases.create_account;

public interface CreateAccountOB {
    CreateAccountOD prepareSuccessView(CreateAccountOD user);

    CreateAccountOD prepareFailView(String error);
}
