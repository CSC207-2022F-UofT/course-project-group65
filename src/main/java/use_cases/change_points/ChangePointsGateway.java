package use_cases.change_points;

import java.io.IOException;

public interface ChangePointsGateway {

    void save(ChangePointsDSID data) throws IOException;

}
