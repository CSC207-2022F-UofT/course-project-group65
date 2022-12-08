package interface_adapters.view_interfaces.main_view_interfaces;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * View interface between the join team use case and the main bracket view.
 */
public interface JoinTeamViewInterface {

    void updateTeamMembers(LinkedHashMap<String, ArrayList<String>> teamToPlayers);

}
