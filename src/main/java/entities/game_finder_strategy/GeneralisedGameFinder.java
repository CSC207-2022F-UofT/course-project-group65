package entities.game_finder_strategy;

import entities.Game;

import java.util.ArrayList;

/**
 * This class represents a generalised game finder.
 */
public class GeneralisedGameFinder<Bracket> implements GameFinder<Bracket> {

    /** The getter method for the game
     * @param gameID - ID of the game
     * @param head - The head game */
    public Game getGame(int gameID, Game head) {
        ArrayList<Game> visited = new ArrayList<>();
        return getGame(gameID, head, visited);
    }
    /** The different getter method for the game
     * @param gameID - ID of the game
     * @param head - The head game
     * @param visited - ArrayList of games that have been visited*/
    public Game getGame(int gameID, Game head, ArrayList<Game> visited) {
        if (head == null) {
            return null;
        } else if (head.getGameID() == gameID) {
            return head;
        } else {
            visited.add(head);
            Game game = getGame(gameID, head.getPrevGame1(), visited);
            if (game != null) {
                return game;
            }
            return getGame(gameID, head.getPrevGame2(), visited);
        }
    }

//    public Game getGame(int gameID, Game head, ArrayList<Game> visited) {
//        if (head == null) {
//            return null;
//        }
//        else if(head.getGameID() == gameID) {
//            return head;
//        }
//        else {
//            visited.add(head);
//            Game game = null;
//            if(!visited.contains(head.getPrevGame1())){
//                game = getGame(gameID, head.getPrevGame1(), visited);
//            }
//            if(game == null && !visited.contains(head.getPrevGame2())){
//                game = getGame(gameID, head.getPrevGame2(), visited);
//            }
//            return game;
//        }
//    }
}
