package entities;

import entities.Game;
import entities.Team;

import java.util.List;

/** An interface for a games factory.
 * It is part of the factory design pattern, and in our program is implemented
 * by the DefaultGamesFactory class.
 */
public interface GamesFactory {

    /** A method to create the games tree for the bracket.
     * @param numTeams - the number of teams in the tournament (determines number of games)
     * @param teams - the list of teams in the tournament
     * @return the head game of the bracket
     */
    Game getGames(int numTeams, List<Team> teams);
}
