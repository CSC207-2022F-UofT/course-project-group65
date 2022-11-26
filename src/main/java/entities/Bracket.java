package entities;

import java.util.ArrayList;
import java.util.List;

public interface Bracket {
/*
    * This interface represents a bracket in a tournament.
 */
    void setTournamentName(String tournamentName);

    String getTournamentName();

    void setTournamentID(int bracketID);

    int getTournamentID();

    void setFinalGame(Game finalGame);

    Game getFinalGame();

    void addTeam(Team team);

    ArrayList<Team> getTeams();

    void setTeamSize(int teamSize);

    int getTeamSize();

    void addReferee(User referee);

    ArrayList<User> getReferees();

    void setWinCondition(int winCondition);

    int getWinCondition();

    void setTournamentCondition(boolean tournamentCondition);

    boolean getTournamentCondition();

    void setPlayerInvite();

    String getPlayerInvite();

    void setObserverInvite();

    String getObserverInvite();
}
