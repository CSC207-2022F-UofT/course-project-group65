package interface_adapters.view_interfaces.main_view_interfaces;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface JoinTeamViewInterface {

    void updateTeamMembers(LinkedHashMap<String, ArrayList<String>> teamToPlayers);

}
