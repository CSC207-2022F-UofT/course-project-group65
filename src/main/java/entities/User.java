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

    String getCurrentTournaments();

    void setCurrentTournaments(String currentTournaments);

    String getBracketRole(int tournamentID);

    void setBracketRole(int tournamentID, String role);

}
