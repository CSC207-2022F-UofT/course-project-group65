package interface_adapters.view_interfaces.main_view_interfaces;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public interface TeamCreationView {
    void updateTeamMembers(LinkedHashMap<String, ArrayList<String>> teamToPlayers);
    void replaceTeam(String newTeam, String oldTeam, LinkedHashMap<Integer, ArrayList<String>> gameToTeams);
}
