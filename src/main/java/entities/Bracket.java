package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
/**
 * This abstract class represents a bracket in a tournament.
 */
public abstract class Bracket implements Serializable {
    /** A string variable for tournament's name */
    String tournamentName;
    /** An int variable for tournament's ID */
    int tournamentID;
    /** A variable game for the final game */
    Game finalGame;
    /** An arraylist for all the teams */
    final ArrayList<Team> teams;
    /** An int variable teamSize for the bracket's maximum team size */
    int teamSize;
    /** An arraylist for all the referees - overseers and observers */
    final ArrayList<User> referees;
    /** An int variable for win condition - the number of points that one team needs to win */
    int winCondition;
    /** A boolean variable represents the tournament condition is started(True) or ended(False) */
    boolean tournamentCondition;
    /** A String for player's invite */
    String playerInvite;
    /** A String for observer's invite */
    String observerInvite;

    /** Constructor for initializing the bracket */
    public Bracket(){
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
    /** This method is for setting the tournament's name */
    public void setTournamentName(String tournamentName) {
    this.tournamentName = tournamentName;
}
    /** This method is for getting the tournament's name */
    public String getTournamentName() {
        return tournamentName;
    }
    /** This method is for setting the tournament's ID */
    public void setTournamentID(int bracketID) {
        this.tournamentID = bracketID;
    }
    /** This method is for getting the tournament's ID */
    public int getTournamentID() {
        return tournamentID;
    }
    /** This method is for setting the final game */
    public void setFinalGame(Game finalGame) {
        this.finalGame = finalGame;
    }
    /** This method is for getting the final game */
    public Game getFinalGame() {
        return finalGame;
    }
    /** This method is for adding the team to the team's list */
    public void addTeam(Team team) {
        teams.add(team);
    }

    /** This method is for getting all the teams from the team's list */
    public ArrayList<Team> getTeams() {
        return teams;
    }
    /** This method is for setting the team's maximum size */
    public void setTeamSize(int teamSize) {
        this.teamSize = teamSize;
    }
    /** This method is for getting the team's maximum size */
    public int getTeamSize() {
        return teamSize;
    }
    /** This method is for adding the referee to the referees' list */
    public void addReferee(User referee) {
        referees.add(referee);
    }
    /** This method is for getting the all referees from the referees' list */
    public ArrayList<User> getReferees() {
        return referees;
    }
    /** This method is for setting the win condition */
    public void setWinCondition(int winCondition) {
        this.winCondition = winCondition;
    }
    /** This method is for getting the win condition */
    public int getWinCondition() {
        return winCondition;
    }
    /** This method is for setting the tournament condition */
    public void setTournamentCondition(boolean tournamentCondition) {
        this.tournamentCondition = tournamentCondition;
    }
    /** This method is for getting the tournament condition */
    public boolean getTournamentCondition() {
        return tournamentCondition;
    }
    /** This method is for setting the player invite */
    public void setPlayerInvite() {
        this.playerInvite = "PL" + tournamentID + this.tournamentName;
    }
    /** This method is for getting the player invite */
    public String getPlayerInvite() {
        return playerInvite;
    }
    /** This method is for setting the observer invite */
    public void setObserverInvite() {
        this.observerInvite = "OB" + tournamentID + this.tournamentName;
    }
    /** This method is for getting the observer invite */
    public String getObserverInvite() {
        return observerInvite;
    }
    /** This method is for getting game by
     * @param gameID - The ID of the game */
    public Game getGame(int gameID) {
        ArrayList<Game> visited = new ArrayList<>();
        return getGame(gameID, finalGame, visited);
    }
    /** This method is for getting game when some games are visited
     * @param gameID - The ID of the game
     * @param  head - The head game
     * @param visited - The games are visited*/
    private Game getGame(int gameID, Game head, ArrayList<Game> visited) {
        if (head == null) {
            return null;
        }
        else if(head.getGameID() == gameID) {
            return head;
        }
        else {
            visited.add(head);
            Game game = null;
            if(!visited.contains(head.getPrevGame1())){
                game = getGame(gameID, head.getPrevGame1(), visited);
            }
            if(game == null && !visited.contains(head.getPrevGame2())){
                game = getGame(gameID, head.getPrevGame2(), visited);
            }
            return game;
        }
    }

    /** This method is for getting the total rounds */
    public int getNumRounds() {
        return finalGame.getGameRound();
    }
    /** This method is for getting the games in specific round number by
     * @param roundNum - The round number */
    public ArrayList<Game> getGamesInRound(int roundNum) {
        HashSet<Game> visited = new HashSet<>();
        ArrayList<Game> games = new ArrayList<>();
        getGamesInRound(finalGame, roundNum, visited, games);
        return games;
    }
    /** This method is for getting the games in specific round number when some games are visited
     * @param roundNum - The round number
     * @param head - The head game
     * @param visited - The games are visited
     * @param games - The list of games*/
    private void getGamesInRound(Game head, int roundNum, HashSet<Game> visited, ArrayList<Game> games) {
        if (head != null) {
            visited.add(head);
            if (head.getGameRound() == roundNum) {
                games.add(head);
                return;
            }
            if (head.getPrevGame1() != null || head.getPrevGame2() != null) {
                if (head.getPrevGame1() != null && !visited.contains(head.getPrevGame1())) {
                    getGamesInRound(head.getPrevGame1(), roundNum, visited, games);
                }
                if (head.getPrevGame2() != null && !visited.contains(head.getPrevGame2())){
                    getGamesInRound(head.getPrevGame2(), roundNum, visited, games);
                }
            }
        }
    }
    /** The method for getting all the games visited */
    public ArrayList<Game> getAllGames(){
        HashSet<Game> visited = new HashSet<>();
        getAllGames(finalGame, visited);
        return new ArrayList<>(visited);
    }
    /** The helper method for getting all the games visited */
    private void getAllGames(Game head, HashSet<Game> visited){
        if (head != null) {
            visited.add(head);
            if (head.getPrevGame1() != null && !visited.contains(head.getPrevGame1())) {
                getAllGames(head.getPrevGame1(), visited);
            }
            if (head.getPrevGame2() != null && !visited.contains(head.getPrevGame2())) {
                getAllGames(head.getPrevGame2(), visited);
            }
        }
    }
}
