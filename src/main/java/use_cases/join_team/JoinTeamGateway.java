package use_cases.join_team;

import java.io.IOException;

public interface JoinTeamGateway {

    void save(JoinTeamDSID data) throws IOException;
}
