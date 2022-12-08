package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
/** This class represents the default user*/
public class DefaultUser implements User, Serializable {
    /** A string for user name */
    private String username;
    /** A string for password */
    private String password;
    /** A hashmap to store bracket roles */
    private final HashMap<Integer, String> bracketRole;
    /** An int for current tournament ID */
    private int currentTournament;
    /** An arraylist for all the current tournaments */
    private final ArrayList<Integer> currentTournaments;
    /** The constructor for default user */
    public DefaultUser() {
        username = "";
        password = "";
        bracketRole = new HashMap<>();
        currentTournament = 0;
        currentTournaments = new ArrayList<>();
    }
    /** The setter and getter methods */
    public void setUsername(String username) {this.username = username;}

    public String getUsername() { return username; }

    public void setPassword(String password) {this.password = password;}

    public String getPassword() { return password; }

    public void setBracketRole(int tournamentID, String role) {
        bracketRole.put(tournamentID, role);
    }

    public String getBracketRole(int tournamentID) {
           return bracketRole.get(tournamentID);
    }

    public void setCurrentTournament(int tournamentID) { this.currentTournament = tournamentID; }

    public int getCurrentTournament() { return currentTournament; }

    public ArrayList<Integer> getAllTournaments() { return currentTournaments; }

    /** This method adds the tournament to the all current tournaments
     * @param  tournamentID - The tournament ID */
    public void addTournament(int tournamentID) { currentTournaments.add(tournamentID); }


    }

