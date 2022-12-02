package useCases.teamCreation;

import java.io.IOException;
/** An interface for the gateway for teamCreation
 */

public interface teamCreationGateway {
    /** Saves the data to the database
     * @param data - the data that gets saved back to the database
     */
    void save(teamCreationDSID data) throws IOException;
}
