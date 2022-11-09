package entities;

import java.util.ArrayList;
import java.util.HashMap;

public class DefaultGame implements Game{
    private int gameID;
    private int gameRound;
    private int numTeams;
    private HashMap<Team, Integer> teams;
    private User observer;
    private Team winner;
    private boolean gameStatus;
    private Game prevGame1;
    private Game prevGame2;

    public DefaultGame() {
        gameID = 0;
        gameRound = 0;
        numTeams = 0;
        teams = new HashMap<Team, Integer>();
        observer = null;
        winner = null;
        gameStatus = false;
        prevGame1 = null;
        prevGame2 = null;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameRound(int gameRound) {
        this.gameRound = gameRound;
    }

    public int getGameRound() {
        return gameRound;
    }

    public void setNumTeams(int numTeams) {
        this.numTeams = numTeams;
    }

    public int getNumTeams() {
        return numTeams;
    }

    public void setTeam(Team team, int points) {
        teams.put(team, points);
    }

//    May need to change getTeams()
    public ArrayList<Team> getTeams() {
        return new ArrayList<Team>(teams.keySet());
    }

    public int getTeamPoints(Team team) {
        return teams.get(team);
    }

    public void setObserver(User observer) {
        this.observer = observer;
    }

    public User getObserver() {
        return observer;
    }

    public void setWinner(Team winner) {
        this.winner = winner;
    }

    public Team getWinner() {
        return winner;
    }

    public void setGameStatus(boolean gameStatus) {
        this.gameStatus = gameStatus;
    }

    public boolean getGameStatus() {
        return gameStatus;
    }

    public void setPrevGame1(Game prevGame1) {
        this.prevGame1 = prevGame1;
    }

    public Game getPrevGame1() {
        return prevGame1;
    }

    public void setPrevGame2(Game prevGame2) {
        this.prevGame2 = prevGame2;
    }

    public Game getPrevGame2() {
        return prevGame2;
    }
}
