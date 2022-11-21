package useCases.CreateAccount;

import entities.AccountRepo;

public interface CreateAccountIB {
    CreateAccountOD create(CreateAccountID requestModel);
}
