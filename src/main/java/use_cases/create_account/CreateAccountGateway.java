package use_cases.create_account;

import java.io.IOException;

public interface CreateAccountGateway {

    void save(CreateAccountDSID data) throws IOException;

}
