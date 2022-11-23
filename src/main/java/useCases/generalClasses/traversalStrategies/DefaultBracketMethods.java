package useCases.generalClasses.traversalStrategies;

import entities.DefaultBracket;
import entities.Game;

import java.util.ArrayList;

public class DefaultBracketMethods implements BracketMethods<DefaultBracket> {
    private DefaultBracket bracket;

    public DefaultBracketMethods(DefaultBracket bracket) {
        this.bracket = bracket;
    }

    @Override
    public ArrayList<Game> getGamesInRound(int roundNum) {
        return getGamesInRound(bracket.getFinalGame(), roundNum);
    }

    private ArrayList<Game> getGamesInRound(Game head, int roundNum) {
        ArrayList<Game> games = new ArrayList<>();
        if (head == null) {
            return games;
        } else if (head.getPrevGame1() == null && head.getPrevGame2() == null) {
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

    @Override
    public ArrayList<ArrayList<Game>> getGamesByRound() {
        int rounds = bracket.getNumRounds();
        ArrayList<ArrayList<Game>> games = new ArrayList<>();

        for(int i=1; i<=rounds; i++){
            games.add(getGamesInRound(i));
        }

        return games;
    }
}
