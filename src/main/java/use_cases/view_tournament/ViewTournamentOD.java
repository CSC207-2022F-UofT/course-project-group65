package use_cases.view_tournament;

import use_cases.general_classes.bundle_bracket_data.BundleBracketData;
import java.util.ArrayList;
import java.util.LinkedHashMap;

public class ViewTournamentOD {
    private final String username;
    private final LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    private final LinkedHashMap<Integer, ArrayList<Integer>> gameToScores;
    private final LinkedHashMap<Integer, String> gameToWinner;
    private final LinkedHashMap<String, ArrayList<String>> teamToPlayers;
    private final ArrayList<String> referees;
    private final LinkedHashMap<Integer, String> gameToReferee;
    private final LinkedHashMap<String, String> roleToInvite;
    private final int tournamentID;
    private final String tournamentName;

    public ViewTournamentOD(String username, BundleBracketData bracketData){
        this.username = username;
        gameToTeams = bracketData.getGameToTeams();
        gameToScores = bracketData.getGameToScores();
        gameToWinner = bracketData.getGameToWinner();
        teamToPlayers = bracketData.getTeamToPlayers();
        referees = bracketData.getReferees();
        gameToReferee = bracketData.getGameToReferee();
        roleToInvite = bracketData.getRoleToInvite();
        tournamentID = bracketData.getTournamentID();
        tournamentName = bracketData.getTournamentName();
    }

    public String getUsername() {
        return username;
    }

    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams() {
        return gameToTeams;
    }

    public LinkedHashMap<Integer, ArrayList<Integer>> getGameToScores() {
        return gameToScores;
    }

    public LinkedHashMap<Integer, String> getGameToWinner() {
        return gameToWinner;
    }

    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers() {
        return teamToPlayers;
    }

    public ArrayList<String> getReferees() {
        return referees;
    }

    public LinkedHashMap<Integer, String> getGameToReferee() {
        return gameToReferee;
    }

    public String getInvite(String role) {
        return roleToInvite.get(role);
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }
}
