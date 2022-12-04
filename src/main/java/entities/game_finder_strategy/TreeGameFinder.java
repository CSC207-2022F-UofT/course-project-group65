package entities.game_finder_strategy;

import entities.Game;

/**
 * This class represents a tree game finder.
 */
public class TreeGameFinder<DefaultBracket> implements GameFinder<DefaultBracket> {
    /** The getter method for the game
     * @param gameID - ID of the game
     * @param head - The head game */
    @Override
    public Game getGame(int gameID, Game head) {
        if (head == null) {
            return null;
        } else if (head.getGameID() == gameID) {
            return head;
        } else {
            Game game = getGame(gameID, head.getPrevGame1());
            if (game != null) {
                return game;
            }
            return getGame(gameID, head.getPrevGame2());
        }
    }
}
