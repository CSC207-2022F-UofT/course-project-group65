package useCases.createBracket;

import entities.Game;
import entities.Team;

import java.util.List;

public interface GamesFactory {
    /*
     * This is a factory for creating a game.
     */

    Game getGames(int numTeams, List<Team> teams);
}
