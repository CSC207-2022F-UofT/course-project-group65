package useCases.LogIn;

import entities.AccountRepo;
import entities.BracketRepo;

import java.util.HashMap;

public class LogInOD {
    private String username;
    private AccountRepo accountRepo;
    private BracketRepo bracketRepo;

    public LogInOD(String username, AccountRepo accountRepo, BracketRepo bracketRepo) {
        this.username = username;
        this.accountRepo = accountRepo;
        this.bracketRepo = bracketRepo;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {this.username = username; }

    public AccountRepo getAccountRepo() {return accountRepo; }

    public void setAccountRepo(AccountRepo accountRepo) {
        this.accountRepo = accountRepo;
    }

    public BracketRepo getBracketRepo() {return bracketRepo; }

    public void setBracketRepo(BracketRepo bracketRepo) {
        this.bracketRepo = bracketRepo;
    }
}
