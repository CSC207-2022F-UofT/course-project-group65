package useCases.generalClasses.traversalStrategies;

import entities.Game;

import java.util.ArrayList;

public class DefaultBracketMethods {


    // Find the game with the given gameID in the tree
    public Game defaultFindGame(int gameID, Game head) {
        if (head == null) {
            return null;
        } else if (head.getGameID() == gameID) {
            return head;
        } else {
            Game game = defaultFindGame(gameID, head.getPrevGame1());
            if (game != null) {
                return game;
            }
            return defaultFindGame(gameID, head.getPrevGame2());
        }
    }

    // Find all the games in the given round/tree level
    public ArrayList<Game> defaultLevelNodes(Game head, int roundNum) {
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
            games.addAll(defaultLevelNodes(head.getPrevGame1(), roundNum));
            games.addAll(defaultLevelNodes(head.getPrevGame2(), roundNum));
            return games;
        }
    }

    // Find the height of the tree
    public int defaultFindHeight(Game head) {
        if (head == null) {
            return 0;
        } else {
            int leftHeight = defaultFindHeight(head.getPrevGame1());
            int rightHeight = defaultFindHeight(head.getPrevGame2());
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }
}
