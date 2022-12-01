package entities.game_finder_strategy;

import entities.Game;

public class TreeGameFinder<DefaultBracket> implements GameFinder<DefaultBracket> {
    /*
        * This class represents a tree game finder.
     */
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
