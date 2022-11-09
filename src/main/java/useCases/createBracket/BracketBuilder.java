package useCases.createBracket;

import entities.Bracket;
import entities.User;

public interface BracketBuilder {
    /*
     * This is an interface for creating a bracket.
     */

    void setName(String tournamentName);

    void setID(int tournamentID);

    void buildTeams(int numTeams);

    void buildGames(int numTeams);

    void setMaxTeamSize(int maxTeamSize);

    void addOverseer(User overseer, int tournamentID);

    void setWinCon(int winCondition);

    Bracket getBracket();

}
