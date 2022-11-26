package useCases.generalClasses.traversalStrategies;

import entities.Bracket;
import entities.Game;

import java.util.ArrayList;

public class DefaultBracketMethods implements BracketMethods{
    private Bracket bracket;

    public DefaultBracketMethods(Bracket bracket){
        this.bracket = bracket;
    }
    // Find all the games in the given round/tree level
    @Override
    public ArrayList<Game> getGamesInRound(int roundNum) {
        return getGamesInRound(bracket.getFinalGame(), roundNum);
    }

    private ArrayList<Game> getGamesInRound(Game head, int roundNum) {
        ArrayList<Game> games = new ArrayList<>();
        if (head == null) {
            return games;
        } else if (head.getPrevGame1() == null) {
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
