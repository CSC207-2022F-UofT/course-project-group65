package useCases.declareWinner;

import java.io.IOException;

public interface DeclareWinnerGateway {

    void save(DeclareWinnerDSID data) throws IOException;

}
