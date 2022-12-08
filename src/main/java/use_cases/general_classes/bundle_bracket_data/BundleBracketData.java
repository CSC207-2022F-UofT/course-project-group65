package use_cases.general_classes.bundle_bracket_data;

import entities.Bracket;
import entities.Game;
import entities.Team;
import entities.User;

import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * A general purpose class that bundles all the data needed to display a bracket in the view.
 */
public class BundleBracketData {
    private final LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    private final LinkedHashMap<Integer, ArrayList<Integer>> gameToScores;
    private final LinkedHashMap<Integer, String> gameToWinner;
    private final LinkedHashMap<String, ArrayList<String>> teamToPlayers;
    private final ArrayList<String> referees;
    private final LinkedHashMap<Integer, String> gameToReferee;
    private final LinkedHashMap<String, String> roleToInvite;
    private int tournamentID;
    private String tournamentName;

    public BundleBracketData(){
        this.gameToTeams = new LinkedHashMap<>();
        this.gameToScores = new LinkedHashMap<>();
        this.gameToWinner = new LinkedHashMap<>();
        this.teamToPlayers = new LinkedHashMap<>();
        this.referees = new ArrayList<>();
        this.gameToReferee = new LinkedHashMap<>();
        this.roleToInvite = new LinkedHashMap<>();
    }

    /**
     * Re-bundles the data from the given bracket into this BundleBracketData object.
     * @param bracket the bracket to bundle data from
     */
    public void bundleBracket(Bracket bracket){
        setGameToTeams(bracket);
        setGameToScores(bracket);
        setGameToWinner(bracket);
        setTeamToPlayers(bracket);
        setReferees(bracket);
        setGameToReferee(bracket);
        setRoleToInvite(bracket);
        tournamentID = bracket.getTournamentID();
        tournamentName = bracket.getTournamentName();
    }

    /**
     * Creates a mapping from gameID to the two teams playing in that game.
     * @param bracket the bracket to bundle data from
     */
    private void setGameToTeams(Bracket bracket){
        ArrayList<String> teams = new ArrayList<>();
        for(Game game: getGames(bracket)){
            for(Team team: game.getTeams()) {
                teams.add(team.getTeamName());
            }
            gameToTeams.put(game.getGameID(), new ArrayList<>(teams));
            teams.clear();
        }
    }

    /**
     * Creates a mapping from gameID to the two scores of that game.
     * @param bracket the bracket to bundle data from
     */
    private void setGameToScores(Bracket bracket){
        ArrayList<Integer> score = new ArrayList<>();
        for(Game game: getGames(bracket)){
            for(Team team: game.getTeams()) {
                score.add(game.getTeamPoints(team));
            }
            gameToScores.put(game.getGameID(), new ArrayList<>(score));
            score.clear();
        }
    }

    /**
     * Creates a mapping from gameID to the winner of that game.
     * @param bracket the bracket to bundle data from
     */
    private void setGameToWinner(Bracket bracket){
        for(Game game: getGames(bracket)){
            if (game.getWinner() != null) {
                gameToWinner.put(game.getGameID(), game.getWinner().getTeamName());
            } else {
                gameToWinner.put(game.getGameID(), null);
            }
        }
    }

    /**
     * Creates a mapping from teamName to the players on that team.
     * @param bracket the bracket to bundle data from
     */
    private void setTeamToPlayers(Bracket bracket){
        ArrayList<String> members = new ArrayList<>();
        for(Team team: bracket.getTeams()){
            for(User member: team.getTeamMembers()){
                members.add(member.getUsername());
            }
            teamToPlayers.put(team.getTeamName(), new ArrayList<>(members));
            members.clear();
        }
    }

    /**
     * Creates a list of all the referees in the bracket.
     * @param bracket the bracket to bundle data from
     */
    private void setReferees(Bracket bracket){
        for(User ref: bracket.getReferees()){
            referees.add(ref.getUsername());
        }
    }

    /**
     * Creates a mapping from gameID to the referee of that game.
     * @param bracket the bracket to bundle data from
     */
    private void setGameToReferee(Bracket bracket){
        for(Game game: getGames(bracket)){
            if (game.getObserver() != null) {
                gameToReferee.put(game.getGameID(), game.getObserver().getUsername());
            } else {
                gameToReferee.put(game.getGameID(), null);
            }
        }
    }

    /**
     * Creates a mapping from role to the invite of that role.
     * @param bracket the bracket to bundle data from
     */
    private void setRoleToInvite(Bracket bracket){
        roleToInvite.put("Player", bracket.getPlayerInvite());
        roleToInvite.put("Observer", bracket.getObserverInvite());
    }

    // Getters for the data
    private ArrayList<Game> getGames(Bracket bracket){
        return bracket.getAllGames();
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

    public LinkedHashMap<String, String> getRoleToInvite() {
        return roleToInvite;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public String getTournamentName() {
        return tournamentName;
    }
}
