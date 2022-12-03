package use_cases.declare_winner;

import java.io.IOException;

public interface DeclareWinnerGateway {

    void save(DeclareWinnerDSID data) throws IOException;

}
