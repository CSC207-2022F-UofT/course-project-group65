package useCases.createBracket;

import entities.DefaultBracket;
import entities.Team;
import entities.User;

import java.util.ArrayList;

public class DefaultBracketBuilder implements BracketBuilder {
    /*
     * This is a class for creating a default bracket.
     */
    private DefaultBracket bracket;

    public DefaultBracketBuilder() {
        this.bracket = new DefaultBracket();
    }

    public void setName(String tournamentName) {
        this.bracket.setTournamentName(tournamentName);
    }

    public void setID(int tournamentID) {
        this.bracket.setTournamentID(tournamentID);
    }

    public ArrayList<Team> buildTeams(int numTeams) {
        // TODO Auto-generated method stub
        return new ArrayList<>();
    }

    public void buildGames(int numTeams, ArrayList<Team> teams) {
        // TODO Auto-generated method stub

    }

    public void setMaxTeamSize(int maxTeamSize) {
        bracket.setTeamSize(maxTeamSize);
    }

    public void addOverseer(User overseer, int tournamentID) {
        overseer.setBracketRole(tournamentID, "Overseer");
        bracket.addReferee(overseer);
    }

    public void setWinCon(int winCondition) {
        bracket.setWinCondition(winCondition);
    }

    public DefaultBracket getBracket() {
        return bracket;
    }

}
