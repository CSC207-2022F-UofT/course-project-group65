package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;

public abstract class Bracket implements Serializable {
/*
    * This abstract class represents a bracket in a tournament.
 */
    String tournamentName;
    int tournamentID;
    Game finalGame;
    ArrayList<Team> teams;
    int teamSize;
    ArrayList<User> referees;
    int winCondition;
    boolean tournamentCondition;
    String playerInvite;
    String observerInvite;

    public Bracket(){
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

    public Game getGame(int gameID) {
        ArrayList<Game> visited = new ArrayList<>();
        return getGame(gameID, finalGame, visited);
    }

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

//    private Game getGame(int gameID, Game head) {
//        if (head == null) {
//            return null;
//        } else if (head.getGameID() == gameID) {
//            return head;
//        } else {
//            Game game = getGame(gameID, head.getPrevGame1());
//            if (game != null) {
//                return game;
//            }
//            return getGame(gameID, head.getPrevGame2());
//        }
//    }

    public int getNumRounds() {
        return finalGame.getGameRound();
    }

    public ArrayList<Game> getGamesInRound(int roundNum) {
        HashSet<Game> visited = new HashSet<>();
        ArrayList<Game> games = new ArrayList<>();
        getGamesInRound(finalGame, roundNum, visited, games);
        return games;
    }

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

    public ArrayList<Game> getAllGames(){
        HashSet<Game> visited = new HashSet<>();
        getAllGames(finalGame, visited);
        return new ArrayList<>(visited);
    }

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
