package entities.game_finder_strategy;

import entities.Game;

import java.util.ArrayList;

/**
 * This interface represents a game finder.
 */
public interface GameFinder<T> {
    /** The abstract getter method for game */
    Game getGame(int gameID, Game head);
}

