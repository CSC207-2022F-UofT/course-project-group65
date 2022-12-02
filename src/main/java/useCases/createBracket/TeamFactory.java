package useCases.createBracket;

import entities.Team;

/** An interface for a team factory.
 * It is part of the factory design pattern, and in our program is implemented
 * by the DefaultTeamFactory class.
 */
public interface TeamFactory {

    /** A method to create a new team.
     * @param teamType - the type of team to create
     * @return a new team
     */
    Team getTeam(String teamType);
}
