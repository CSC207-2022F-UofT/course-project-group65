package use_cases.advance_team;

import java.io.IOException;

/**
 * Gateway interface for AdvanceTeam use case
 */
public interface AdvanceTeamGateway {

    /**
     * Defines the method to save.
     *
     * @throws IOException if there is an error reading the team file
     */
    void save(AdvanceTeamDSID data) throws IOException;

}
