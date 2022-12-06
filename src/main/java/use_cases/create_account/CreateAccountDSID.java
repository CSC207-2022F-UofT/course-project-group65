package use_cases.create_account;

import entities.AccountRepo;

public class CreateAccountDSID {

    private final AccountRepo accounts;

    public CreateAccountDSID(AccountRepo accounts) {
        this.accounts = accounts;
    }

    public AccountRepo getAccounts() {
        return accounts;
    }

}
