package use_cases.join_team;

import java.io.IOException;

/**
 * An interface for the gateway for JoinTeam.
 */
public interface JoinTeamGateway {
    /**
     * Saves the data to the database
     *
     * @param data - the data source interface data
     */
    void save(JoinTeamDSID data) throws IOException;
}
