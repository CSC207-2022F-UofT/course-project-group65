package useCases.createBracket;

import entities.Bracket;
import entities.Team;
import entities.User;

import java.util.ArrayList;

public interface BracketBuilder {
    /*
     * This is an interface for creating a bracket.
     */

    void setName(String tournamentName);

    void setID(int tournamentID);

    ArrayList<Team> buildTeams(int numTeams);

    void buildGames(int numTeams, ArrayList<Team> teams);

    void setMaxTeamSize(int maxTeamSize);

    void addOverseer(User overseer, int tournamentID);

    void setWinCon(int winCondition);

    Bracket getBracket();

}
