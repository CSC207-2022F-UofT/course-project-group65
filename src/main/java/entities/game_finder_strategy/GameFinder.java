package entities.game_finder_strategy;

import entities.Game;

import java.util.ArrayList;

public interface GameFinder<T> {
    /*
        * This interface represents a game finder.
     */
    Game getGame(int gameID, Game head);
}

