package use_cases.create_account;

import entities.AccountRepo;

public class CreateAccountDSID {

    AccountRepo accounts;

    public CreateAccountDSID(AccountRepo accounts) {
        this.accounts = accounts;
    }

    public AccountRepo getAccounts() {
        return accounts;
    }

    public void setAccounts(AccountRepo accounts) {
        this.accounts = accounts;
    }
}
