package useCases.generalClasses;

import entities.Game;

import java.util.ArrayList;

public class DefaultBracketMethods implements TreeMethods{
    @Override
    public Game findGame(int gameID, Game head) {
        if (head == null) {
            return null;
        } else if (head.getGameID() == gameID) {
            return head;
        } else {
            Game game = findGame(gameID, head.getPrevGame1());
            if (game != null) {
                return game;
            }
            return findGame(gameID, head.getPrevGame2());
        }
    }

    @Override
    public ArrayList<Game> levelNodes(Game head, int roundNum) {
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
            games.addAll(levelNodes(head.getPrevGame1(), roundNum));
            games.addAll(levelNodes(head.getPrevGame2(), roundNum));
            return games;
        }
    }

    @Override
    public int findHeight(Game head) {
        if (head == null) {
            return 0;
        } else {
            int leftHeight = findHeight(head.getPrevGame1());
            int rightHeight = findHeight(head.getPrevGame2());
            if (leftHeight > rightHeight) {
                return leftHeight + 1;
            } else {
                return rightHeight + 1;
            }
        }
    }
}
