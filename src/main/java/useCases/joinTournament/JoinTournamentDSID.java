package useCases.joinTournament;

import entities.AccountRepo;
import entities.BracketRepo;

public class JoinTournamentDSID {

    private final BracketRepo bracketRepo;
    private final AccountRepo accountRepo;

    public JoinTournamentDSID(BracketRepo bracketRepo, AccountRepo accountRepo){
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

    public BracketRepo getBracketRepo(){
        return this.bracketRepo;
    }

    public AccountRepo getAccountRepo(){
        return this.accountRepo;
    }

}
