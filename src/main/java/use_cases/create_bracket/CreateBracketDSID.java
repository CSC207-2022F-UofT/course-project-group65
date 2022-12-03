package use_cases.create_bracket;

import entities.AccountRepo;
import entities.BracketRepo;

/**
 * This is a class for accessing the input data from the Database.
 */
public class CreateBracketDSID {

    /** The bracket repo used in accessing the data */
    private final BracketRepo bracketRepo;
    /** The account repo used in accessing the data */
    private final AccountRepo accountRepo;

    /** Constructor for the CreateBracketDSID class
     * @param bracketRepo - the bracket repo used in accessing the data
     * @param accountRepo - the account repo used in accessing the data
     */
    public CreateBracketDSID(BracketRepo bracketRepo, AccountRepo accountRepo) {
        this.bracketRepo = bracketRepo;
        this.accountRepo = accountRepo;
    }

    /** Returns the bracket repo used for accessing the data
     * @return the bracket repo used for accessing the data
     */
    public BracketRepo getBracketRepo() {
        return bracketRepo;
    }

    /** Returns the account repo used for accessing the data
     * @return the account repo used for accessing the data
     */
    public AccountRepo getAccountRepo() {
        return accountRepo;
    }

}
