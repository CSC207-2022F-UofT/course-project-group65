package useCases.createBracket;

import entities.User;

public interface BracketBuilder {
    /*
     * This is an interface for creating a bracket.
     */

    void setName(String tournamentName);

    void setID(int tournamentID);

    void setGames(int numTeams);

    void setTeams(int numTeams);

    void setMaxTeamSize(int maxTeamSize);

    void addOverseer(User overseer);

    void setWinCon(int winCondition);


}
