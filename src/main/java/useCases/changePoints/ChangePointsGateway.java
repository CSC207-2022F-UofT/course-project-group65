package useCases.changePoints;

import java.io.IOException;

public interface ChangePointsGateway {

    void save(ChangePointsDSID data) throws IOException;

}
