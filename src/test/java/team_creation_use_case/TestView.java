package team_creation_use_case;

import interface_adapters.view_interfaces.main_view_interfaces.TeamCreationView;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class TestView implements TeamCreationView {

    @Override
    public void updateTeamMembers(LinkedHashMap<String, ArrayList<String>> teamToPlayers) {

    }

    @Override
    public void replaceTeam(String newTeam, String oldTeam, LinkedHashMap<Integer, ArrayList<String>> gameToTeams) {
    }
}
