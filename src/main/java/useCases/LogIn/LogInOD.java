package useCases.LogIn;

import java.util.HashMap;

public class LogInOD {
    private String username;
    private HashMap<Integer, String> bracketRole;
    private int currentTournament;

    public LogInOD(String username, HashMap<Integer, String> bracketRole, int currentTournament) {
        this.username = username;
        this.bracketRole = bracketRole;
        this.currentTournament = currentTournament;
    }

    public String getUsername() { return username; }

    public void setUsername(String username) {this.username = username; }

    public void setBracketRole(int tournamentID, String role) {
        bracketRole.put(tournamentID, role);
    }

    public String getBracketRole(int tournamentID) {
        return bracketRole.get(tournamentID);
    }

    public void setCurrentTournament(int tournamentID) { this.currentTournament = tournamentID; }

    public int getCurrentTournament() { return currentTournament; }
}
