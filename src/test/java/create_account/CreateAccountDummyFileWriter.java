package create_account;

import entities.AccountRepo;
import use_cases.create_account.CreateAccountDSID;
import use_cases.create_account.CreateAccountGateway;

import java.io.IOException;
import java.util.HashMap;

public class CreateAccountDummyFileWriter implements CreateAccountGateway {
    final private HashMap<AccountRepo, CreateAccountDSID> repo = new HashMap<>();
    @Override
    public void save(CreateAccountDSID dsid) throws IOException {
        System.out.println("Save " + dsid.getAccounts());
        repo.put(dsid.getAccounts(), dsid);
    }
}
