package useCases.createBracket;

import entities.AccountRepo;
import entities.BracketRepo;

public class CreateBracketDSID {

    private BracketRepo bracketRepo;
    private AccountRepo accountRepo;

    public CreateBracketDSID(BracketRepo bracketRepo, AccountRepo accountRepo) {
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

    public BracketRepo getBracketRepo() {
        return bracketRepo;
    }

    public AccountRepo getAccountRepo() {
        return accountRepo;
    }

}
