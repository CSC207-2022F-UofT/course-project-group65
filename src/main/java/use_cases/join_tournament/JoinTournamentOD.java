package use_cases.join_tournament;

import use_cases.general_classes.bundle_bracket_data.BundleBracketData;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * The output data of the join tournament use case.
 */
public class JoinTournamentOD {
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

    /**
     * The constructor of the output data of the join tournament use case.
     *
     * @param username      The username of the current user.
     * @param bracketData   The data of the bracket that needs to be displayed
     */
    public JoinTournamentOD(String username, BundleBracketData bracketData){
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

    /**
     * Returns the username of the current user.
     * @return The username of the current user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Returns a map of games to teams.
     * @return The map of games to teams
     */
    public LinkedHashMap<Integer, ArrayList<String>> getGameToTeams() {
        return gameToTeams;
    }

    /**
     * Returns a map of games to scores.
     * @return The map of games to scores
     */
    public LinkedHashMap<Integer, ArrayList<Integer>> getGameToScores() {
        return gameToScores;
    }

    /**
     * Returns a map of games to winner.
     * @return The map of games to winner
     */
    public LinkedHashMap<Integer, String> getGameToWinner() {
        return gameToWinner;
    }

    /**
     * Returns a map of teams to players.
     * @return The map of teams to players
     */
    public LinkedHashMap<String, ArrayList<String>> getTeamToPlayers() {
        return teamToPlayers;
    }

    /**
     * Returns all referees.
     * @return All the referees
     */
    public ArrayList<String> getReferees() {
        return referees;
    }

    /**
     * Returns a map of games to referee.
     * @return The map of games to referee
     */
    public LinkedHashMap<Integer, String> getGameToReferee() {
        return gameToReferee;
    }

    /**
     * Returns the invite for the specified user type.
     * @return The invite
     */
    public String getInvite(String role) {
        return roleToInvite.get(role);
    }

    /**
     * Returns the tournament ID.
     * @return The tournament ID
     */
    public int getTournamentID() {
        return tournamentID;
    }

    /**
     * Returns the tournament name.
     * @return The tournament name
     */
    public String getTournamentName() {
        return tournamentName;
    }
}