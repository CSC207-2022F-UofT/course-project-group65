package useCases.generalClasses.traversalStrategies;

import entities.Bracket;
import entities.Game;

import java.util.ArrayList;

public interface BracketMethods<T extends Bracket> {
    ArrayList<Game> getGamesInRound(int roundNum);

    ArrayList<ArrayList<Game>> getGamesByRound();
}




