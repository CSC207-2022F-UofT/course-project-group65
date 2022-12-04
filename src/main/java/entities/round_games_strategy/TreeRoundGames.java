package entities.round_games_strategy;

import entities.Game;

import java.util.ArrayList;
/**
 * This class represents a tree round games.
 */
public class TreeRoundGames<DefaultBracket> implements RoundGames<DefaultBracket> {
    /** This method is used to get all the games in that roundNum
     * @param  head- The head game
     * @param roundNum - Game round number of the bracket*/
    public ArrayList<Game> getGamesInRound(Game head, int roundNum) {
        ArrayList<Game> games = new ArrayList<>();
        if (head == null) {
            return games;
        } else if (head.getPrevGame1() == null) { //Only need to check 1 side with default bracket
            if (head.getGameRound() == roundNum) {
                games.add(head);
            }
            return games;
        } else {
            if (head.getGameRound() == roundNum) {
                games.add(head);
            }
            games.addAll(getGamesInRound(head.getPrevGame1(), roundNum));
            games.addAll(getGamesInRound(head.getPrevGame2(), roundNum));
            return games;
        }
    }

}
