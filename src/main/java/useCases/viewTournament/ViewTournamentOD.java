package useCases.viewTournament;

import entities.AccountRepo;
import entities.BracketRepo;

public class ViewTournamentOD {
    String username;
    AccountRepo accounts;
    BracketRepo brackets;
    int tournamentID;
    String role;

    public ViewTournamentOD(String username, AccountRepo accounts, BracketRepo brackets,
                            int tournamentID, String role){
        this.username = username;
        this.accounts = accounts;
        this.brackets = brackets;
        this.tournamentID = tournamentID;
        this.role = role;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public AccountRepo getAccounts() {
        return accounts;
    }

    public void setAccounts(AccountRepo accounts) {
        this.accounts = accounts;
    }

    public BracketRepo getBrackets() {
        return brackets;
    }

    public void setBrackets(BracketRepo brackets) {
        this.brackets = brackets;
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
