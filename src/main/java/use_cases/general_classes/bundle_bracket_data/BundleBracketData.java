package use_cases.general_classes.bundle_bracket_data;

import entities.Bracket;
import entities.Game;
import entities.Team;
import entities.User;
//import useCases.generalClasses.traversalStrategies.BracketMethods;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class BundleBracketData {
    private LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    private LinkedHashMap<Integer, ArrayList<Integer>> gameToScores;
    private LinkedHashMap<Integer, String> gameToWinner;
    private LinkedHashMap<String, ArrayList<String>> teamToPlayers;
    private ArrayList<String> referees;
    private LinkedHashMap<Integer, String> gameToReferee;
    private LinkedHashMap<String, String> roleToInvite;
    int tournamentID;
    String tournamentName;

    public BundleBracketData(){
        this.gameToTeams = new LinkedHashMap<>();
        this.gameToScores = new LinkedHashMap<>();
        this.gameToWinner = new LinkedHashMap<>();
        this.teamToPlayers = new LinkedHashMap<>();
        this.referees = new ArrayList<>();
        this.gameToReferee = new LinkedHashMap<>();
        this.roleToInvite = new LinkedHashMap<>();
    }

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

    private void setGameToWinner(Bracket bracket){
        for(Game game: getGames(bracket)){
            if (game.getWinner() != null) {
                gameToWinner.put(game.getGameID(), game.getWinner().getTeamName());
            } else {
                gameToWinner.put(game.getGameID(), null);
            }
        }
    }

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

    private void setReferees(Bracket bracket){
        for(User ref: bracket.getReferees()){
            referees.add(ref.getUsername());
        }
    }

    private void setGameToReferee(Bracket bracket){
        for(Game game: getGames(bracket)){
            if (game.getObserver() != null) {
                gameToReferee.put(game.getGameID(), game.getObserver().getUsername());
            } else {
                gameToReferee.put(game.getGameID(), null);
            }
        }
    }

    private void setRoleToInvite(Bracket bracket){
        roleToInvite.put("Player", bracket.getPlayerInvite());
        roleToInvite.put("Observer", bracket.getObserverInvite());
    }

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
