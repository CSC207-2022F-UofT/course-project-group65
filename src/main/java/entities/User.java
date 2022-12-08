package entities;

import java.util.List;
/**
 * This interface represents a User in a Bracket.
 */
public interface User {
    /** The getter and setter methods*/
    String getUsername();

    void setUsername(String username);

    String getPassword();

    void setPassword(String password);

    String getBracketRole(int tournamentID);

    void setBracketRole(int tournamentID, String role);

    void setCurrentTournament(int tournamentID);

    int getCurrentTournament();

    List<Integer> getAllTournaments();

    /** This abstract method add a tournament to a user's tournament list*/
    void addTournament(int tournamentID);

}
