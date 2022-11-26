package entities;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Bracket implements Serializable {
/*
    * This interface represents a bracket in a tournament.
 */
private String tournamentName;
    int tournamentID;
    Game finalGame;
    ArrayList<Team> teams;
    int teamSize;
    ArrayList<User> referees;
    int winCondition;
    boolean tournamentCondition;
    String playerInvite;
    String observerInvite;

    public Bracket() {
        tournamentName = "";
        tournamentID = 0;
        finalGame = null;
        teams = new ArrayList<>();
        teamSize = 0;
        referees = new ArrayList<>();
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

    public Game getGame(int gameID) {
        return getGame(gameID, finalGame);
    }

    private Game getGame(int gameID, Game head){
        if (head == null) {
            return null;
        } else if (head.getGameID() == gameID) {
            return head;
        } else {
            Game game = getGame(gameID, head.getPrevGame1());
            if (game != null) {
                return game;
            }
            return getGame(gameID, head.getPrevGame2());
        }
    }

    public int getNumRounds() {
        return finalGame.getGameRound();
    }

}
