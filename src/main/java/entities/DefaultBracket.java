package entities;

import java.io.Serializable;

public class DefaultBracket extends Bracket implements Serializable {
    public DefaultBracket() {
        super();
    }

    @Override
    public Game getGame(int gameID) {
        return getGame(gameID, finalGame);
    }

    private Game getGame(int gameID, Game head){
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

