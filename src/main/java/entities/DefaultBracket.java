package entities;

import java.util.List;

public class DefaultBracket implements Bracket {
    private String tournamentName;
    private int tournamentID;
    private Game finalGame;
    private List<Team> teams;
    private int teamSize;
    private List<User> referees;
    private int winCondition;
    private boolean tournamentCondition;

    public DefaultBracket() {
        tournamentName = "";
        tournamentID = 0;
        finalGame = null;
        teams = null;
        teamSize = 0;
        referees = null;
        winCondition = 0;
        tournamentCondition = false;
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

    public List<Team> getTeams() {
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

    public List<User> getReferees() {
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

}

