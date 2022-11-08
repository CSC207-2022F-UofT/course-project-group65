package entities;

import java.util.List;

public interface User {
    /*
     * This interface represents a User in a Bracket.
     */

    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getBracketRole(int tournamentID);

    void setBracketRole(int tournamentID, String role);

    void setCurrentTournament(int tournamentID);

    int getCurrentTournament();

    void addTournament(int tournamentID);

    List<Integer> getAllTournaments();

}
