package entities;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class DefaultUser implements User, Serializable {
    private String username;
    private String password;
    private HashMap<Integer, String> bracketRole;
    private int currentTournament;
    private ArrayList<Integer> currentTournaments;

    public DefaultUser() {
        username = "";
        password = "";
        bracketRole = new HashMap<Integer, String>();
        currentTournament = 0;
        currentTournaments = new ArrayList<Integer>();
    }
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

    public void addTournament(int tournamentID) { currentTournaments.add(tournamentID); }

    public ArrayList<Integer> getAllTournaments() { return currentTournaments; }
    }

