package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
/** This class represents the default game */
public class DefaultGame implements Game, Serializable {
    /** An int variable for game ID */
    private int gameID;
    /** An int variable for game round */
    private int gameRound;
    /** An int variable for number of teams */
    private int numTeams;
    /** The linkedhashmap for teams */
    private final LinkedHashMap<Team, Integer> teams;
    /** A user - Observer */
    private User observer;
    /** The winner team of a game*/
    private Team winner;
    /** A boolean for game status which is ended or not*/
    private boolean gameStatus;
    /** A game variable for previous game1*/
    private Game prevGame1;
    /** A game variable for previous game2*/
    private Game prevGame2;
    /** The constructor for the default game*/
    public DefaultGame() {
        gameID = 0;
        gameRound = 0;
        numTeams = 0;
        teams = new LinkedHashMap<Team, Integer>();
        observer = null;
        winner = null;
        gameStatus = false;
        prevGame1 = null;
        prevGame2 = null;
    }

    /** The setter and getter methods for all the variables*/
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
