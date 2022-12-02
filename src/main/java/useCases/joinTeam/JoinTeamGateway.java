package useCases.joinTeam;

import java.io.IOException;

public interface JoinTeamGateway {

    void save(JoinTeamDSID data) throws IOException;
}
