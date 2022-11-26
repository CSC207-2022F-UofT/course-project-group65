package useCases.CreateAccount;

import java.io.IOException;

public interface CreateAccountGateway {

    void save(CreateAccountDSID data) throws IOException;

}
