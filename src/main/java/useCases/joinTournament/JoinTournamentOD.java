package useCases.joinTournament;

public class JoinTournamentOD {
    int tournamentID;
    String role;

    public JoinTournamentOD(int tournamentID, String role){
        this.tournamentID = tournamentID;
        this.role = role;
    }

    public int getTournamentID() {
        return tournamentID;
    }

    public void setTournamentID(int tournamentID) {
        this.tournamentID = tournamentID;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
