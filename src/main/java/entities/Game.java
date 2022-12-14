package entities;

import java.util.ArrayList;
/**
 * This interface represents a game in a Bracket. It follows a similar structure to a Tree.
 */
public interface Game{
    /** The setter and getter methods */
    void setGameID(int gameID);

    int getGameID();

    void setGameRound(int gameRound);

    int getGameRound();

    void setNumTeams(int numTeams);

    int getNumTeams();

    void setTeam(Team team, int points);

    ArrayList<Team> getTeams();

    int getTeamPoints(Team team);

    void setObserver(User observer);

    User getObserver();

    void setWinner(Team winner);

    Team getWinner();

    void setGameStatus(boolean gameStatus);

    boolean getGameStatus();

    void setPrevGame1(Game prevGame1);

    Game getPrevGame1();

    void setPrevGame2(Game prevGame2);

    Game getPrevGame2();
}
