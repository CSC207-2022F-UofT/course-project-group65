package useCases.endTourn;

import java.io.IOException;

public interface EndTournGateway {
    void save(EndTournDSID data) throws IOException;
}
