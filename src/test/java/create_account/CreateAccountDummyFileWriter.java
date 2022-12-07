package create_account;

import entities.AccountRepo;
import use_cases.create_account.CreateAccountDSID;
import use_cases.create_account.CreateAccountGateway;

import java.io.IOException;
import java.util.HashMap;

/**
 * This class represents the createAccount file writer for the test use
 */
public class CreateAccountDummyFileWriter implements CreateAccountGateway {
    /**
     * This hashmap variable is for storing bracketRepos
     */
    final private HashMap<AccountRepo, CreateAccountDSID> repo = new HashMap<>();

    /**
     * Write the given data to the file.
     *
     * @param dsid The data to write
     * @throws IOException If an error occurs while writing to the file
     */
    @Override
    public void save(CreateAccountDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getAccounts());
        repo.put(dsid.getAccounts(), dsid);
    }
}
