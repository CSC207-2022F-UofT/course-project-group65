package useCases.viewTournament;

import entities.AccountRepo;
import entities.BracketRepo;
import useCases.generalClasses.bundleBracketData.BundleBracketData;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

public class ViewTournamentOD {
    String username;
    AccountRepo accounts;
    BracketRepo brackets;
    LinkedHashMap<Integer, ArrayList<String>> gameToTeams;
    LinkedHashMap<Integer, ArrayList<Integer>> gameToScores;
    LinkedHashMap<Integer, String> gameToWinner;
    LinkedHashMap<String, ArrayList<String>> teamToPlayers;
    ArrayList<String> referees;
    LinkedHashMap<Integer, String> gameToReferee;
    LinkedHashMap<String, String> roleToInvite;
    int tournamentID;
    String tournamentName;
    List<Integer> tournamentNames;

    public ViewTournamentOD(String username, AccountRepo accounts, BracketRepo brackets, BundleBracketData bracketData,
                            List<Integer> currentTournaments){
        this.username = username;
        this.accounts = accounts;
        this.brackets = brackets;
        gameToTeams = bracketData.getGameToTeams();
        gameToScores = bracketData.getGameToScores();
        gameToWinner = bracketData.getGameToWinner();
        teamToPlayers = bracketData.getTeamToPlayers();
        referees = bracketData.getReferees();
        gameToReferee = bracketData.getGameToReferee();
        roleToInvite = bracketData.getRoleToInvite();
        tournamentID = bracketData.getTournamentID();
        tournamentName = bracketData.getTournamentName();
        tournamentNames = currentTournaments;
    }
    public ArrayList<Integer> getTournamentNames(){
        return (ArrayList<Integer>) tournamentNames;
    }

    public String getUsername() {
        return username;
    }

    public AccountRepo getAccounts() {
        return accounts;
    }

    public BracketRepo getBrackets() {
        return brackets;
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
