package entities;

import java.io.Serializable;
import java.util.ArrayList;

public class DefaultBracket implements Bracket, Serializable {
    private String tournamentName;
    private int tournamentID;
    private Game finalGame;
    private ArrayList<Team> teams;
    private int teamSize;
    private ArrayList<User> referees;
    private int winCondition;
    private boolean tournamentCondition;
    private String playerInvite;

    private String observerInvite;

    public DefaultBracket() {
        tournamentName = "";
        tournamentID = 0;
        finalGame = null;
        teams = new ArrayList<Team>();
        teamSize = 0;
        referees = new ArrayList<User>();
        winCondition = 0;
        tournamentCondition = false;
        playerInvite = "";
        observerInvite = "";

    }

    public void setTournamentName(String tournamentName) {
        this.tournamentName = tournamentName;
    }

    public String getTournamentName() {
        return tournamentName;
    }

    public void setTournamentID(int bracketID) {
        this.tournamentID = bracketID;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setFinalGame(Game finalGame) {
        this.finalGame = finalGame;
    }

    public Game getFinalGame() {
        return finalGame;
    }

    public void addTeam(Team team) {
        teams.add(team);
    }

    public ArrayList<Team> getTeams() {
        return teams;
    }

    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }

    public int getTeamSize() {
        return teamSize;
    }

    public void addReferee(User referee) {
        referees.add(referee);
    }

    public ArrayList<User> getReferees() {
        return referees;
    }

    public void setWinCondition(int winCondition) {
        this.winCondition = winCondition;
    }

    public int getWinCondition() {
        return winCondition;
    }

    public void setTournamentCondition(boolean tournamentCondition) {
        this.tournamentCondition = tournamentCondition;
    }

    public boolean getTournamentCondition() {
        return tournamentCondition;
    }

    public void setPlayerInvite() {
        this.playerInvite = "PL" + tournamentID + this.tournamentName;
    }

    public String getPlayerInvite() {
        return playerInvite;
    }

    public void setObserverInvite() {
        this.observerInvite = "OB" + tournamentID + this.tournamentName;
    }

    public String getObserverInvite() {
        return observerInvite;
    }

}

