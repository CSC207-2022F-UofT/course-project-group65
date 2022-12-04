package entities.round_games_strategy;

import entities.Game;

import java.util.ArrayList;
/**
 * This interface represents a round game.
 */
public interface RoundGames<T> {

    ArrayList<Game> getGamesInRound(Game head, int roundNum);


}
