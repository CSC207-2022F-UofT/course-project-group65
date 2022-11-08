package entities;

import java.util.HashMap;
import java.util.List;

public class DefaultUser implements User{
    private String username;
    private String password;
    private HashMap<Integer, String> bracketRole;
    private String currentTournaments;

    public DefaultUser() {
        username = "";
        password = "";
        bracketRole = new HashMap<Integer, String>();
        currentTournaments = "";
    }
    public void setUsername(String username) {this.username = username;}

    public String getUsername() { return username; }

    public void setPassword(String password) {this.password = password;}

    public String getPassword() { return password; }

    public void setBracketRole(int tournamentID, String role) {
        bracketRole.put(tournamentID, role);
    }

    public List<Integer> getBracketRole() {
        return (List<Integer>) bracketRole.keySet();
    }

    public void setCurrentTournaments(String currentTournament) { this.currentTournaments = currentTournaments; }

    public String getCurrentTournaments() { return currentTournaments; }
    }

