package useCases.generalClasses.traversalStrategies;

import entities.DefaultBracket;
import entities.Game;

import java.util.ArrayList;

public class DefaultBracketMethods extends BracketMethods {
    public DefaultBracketMethods(DefaultBracket bracket) {
        super(bracket);
    }

//    @Override
//    public ArrayList<Game> getGamesInRound(int roundNum) {
//        return getGamesInRound(bracket.getFinalGame(), roundNum);
//    }
//
//    private ArrayList<Game> getGamesInRound(Game head, int roundNum) {
//        ArrayList<Game> games = new ArrayList<>();
//        if (head == null) {
//            return games;
//        } else if (head.getPrevGame1() == null) { //Only need to check 1 side with default bracket
//            if (head.getGameRound() == roundNum) {
//                games.add(head);
//            }
//            return games;
//        } else {
//            if (head.getGameRound() == roundNum) {
//                games.add(head);
//            }
//            games.addAll(getGamesInRound(head.getPrevGame1(), roundNum));
//            games.addAll(getGamesInRound(head.getPrevGame2(), roundNum));
//            return games;
//        }
//    }
}
