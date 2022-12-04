package entities;

import java.io.Serializable;
import java.util.ArrayList;
/** This class represents the default bracket */
public class DefaultBracket extends Bracket implements Serializable {
    /** Call the constructor from super class*/
    public DefaultBracket() {
        super();
    }
    /** Getting the game by
     * @param gameID - The ID of the game */
    @Override
    public Game getGame(int gameID) {
        return getGame(gameID, finalGame);
    }
    /** Getting the game with the head game by
     * @param gameID - The ID of the game
     * @param  head - The head game */
    private Game getGame(int gameID, Game head){
        if (head == null) {
            return null;
        } else if (head.getGameID() == gameID) {
            return head;
        } else {
            Game game = getGame(gameID, head.getPrevGame1());
            if (game != null) {
                return game;
            }
            return getGame(gameID, head.getPrevGame2());
        }
    }
    /** This method is for getting the games in specific round number
     * @param roundNum - The round number
     */
    @Override
    public ArrayList<Game> getGamesInRound(int roundNum) {
        return getGamesInRound(finalGame, roundNum);
    }
    /** This method is for getting the games in specific round number with the head game
     * @param roundNum - The round number
     * @param head - The head game
     */
    private ArrayList<Game> getGamesInRound(Game head, int roundNum) {
        ArrayList<Game> games = new ArrayList<>();
        if (head == null) {
            return games;
        } else if (head.getPrevGame1() == null) { //Only need to check 1 side with default bracket
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

