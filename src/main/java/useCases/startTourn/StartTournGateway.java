package useCases.startTourn;

import java.io.IOException;

public interface StartTournGateway {
    void save(StartTournDSID data) throws IOException;
}
