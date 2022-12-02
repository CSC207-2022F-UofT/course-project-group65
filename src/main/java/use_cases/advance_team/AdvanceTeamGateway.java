package use_cases.advance_team;

import java.io.IOException;

public interface AdvanceTeamGateway {

    void save(AdvanceTeamDSID data) throws IOException;

}
