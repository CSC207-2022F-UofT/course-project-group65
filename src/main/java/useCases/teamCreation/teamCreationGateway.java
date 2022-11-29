package useCases.teamCreation;

import java.io.IOException;

public interface teamCreationGateway {

    void save(teamCreationDSID data) throws IOException;
}
