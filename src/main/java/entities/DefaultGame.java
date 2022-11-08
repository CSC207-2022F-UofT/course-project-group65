package entities;

public class DefaultGame implements Game{
    private int gameID;
    private int gameRound;
    private int numTeams;
    private Team team1;
    private Team team2;
    private User observer;
    private Team winner;
    private boolean gameStatus;
    private Game prevGame1;
    private Game prevGame2;

    public DefaultGame() {
        gameID = 0;
        gameRound = 0;
        numTeams = 0;
        team1 = null;
        team2 = null;
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

    public void setTeam1(Team team1) {
        this.team1 = team1;
    }

    public Team getTeam1() {
        return team1;
    }

    public void setTeam2(Team team2) {
        this.team2 = team2;
    }

    public Team getTeam2() {
        return team2;
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
