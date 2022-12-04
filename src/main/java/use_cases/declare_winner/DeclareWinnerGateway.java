package use_cases.declare_winner;

import java.io.IOException;

/**
 * The gateway interface for the DeclareWinner use case.
 */
public interface DeclareWinnerGateway {

    /**
     * Defines the method to save information following the run of the use case.
     */
    void save(DeclareWinnerDSID data) throws IOException;

}
