package useCases.teamCreation;
import entities .*;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class teamCreationOD {
    private final String username;
    private final String newTeam;
    private final String oldTeam;
    private final LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    private final LinkedHashMap<String, ArrayList<String>> teamToPlayers;

    public teamCreationOD(String username, String newTeam, String oldTeam,
                          LinkedHashMap<Integer, ArrayList<String>> gameToTeams,
                          LinkedHashMap<String, ArrayList<String>> teamToPlayers){
        this.username = username;
        this.newTeam = newTeam;
        this.oldTeam = oldTeam;
        this.gameToTeams =  gameToTeams;
        this.teamToPlayers = teamToPlayers;
    }

    public String getUsername(){
        return username;
    }

    public String getNewTeam() {
        return newTeam;
    }

    public String getOldTeam() {
        return oldTeam;
    }

    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams(){
        return gameToTeams;
    }

    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers(){
        return teamToPlayers;
    }
}
