package entities.round_games_strategy;

import entities.Game;

import java.util.ArrayList;
import java.util.HashSet;
/**
 * This class represents a generalised round games.
 */
public class GeneralisedRoundGames<Bracket> implements RoundGames<Bracket> {
    /** This method is used to get all the games in that roundNum
     * @param  head- The head game
     * @param roundNum - Game round number of the bracket*/
    public ArrayList<Game> getGamesInRound(Game head, int roundNum) {
        HashSet<Game> visited = new HashSet<>();
        ArrayList<Game> games = new ArrayList<>();
        getGamesInRound(head, roundNum, visited, games);
        return games;
    }
    /** This method is used to get all the games in that roundNum when some games have been visited
     * @param  head- The head game
     * @param roundNum - Game round number of the bracket*/
    private void getGamesInRound(Game head, int roundNum, HashSet<Game> visited, ArrayList<Game> games) {
        if (head != null) {
            visited.add(head);
            if (head.getGameRound() == roundNum) {
                games.add(head);
                return;
            }
            if (head.getPrevGame1() != null || head.getPrevGame2() != null) {
                if (head.getPrevGame1() != null && !visited.contains(head.getPrevGame1())) {
                    getGamesInRound(head.getPrevGame1(), roundNum, visited, games);
                }
                if (head.getPrevGame2() != null && !visited.contains(head.getPrevGame2())){
                    getGamesInRound(head.getPrevGame2(), roundNum, visited, games);
                }
            }
        }
    }

}
