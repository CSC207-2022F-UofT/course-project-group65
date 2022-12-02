package entities.round_games_strategy;

import entities.Game;

import java.util.ArrayList;
import java.util.HashSet;

public class GeneralisedRoundGames<Bracket> implements RoundGames<Bracket> {


    /*
        * This class represents a generalised round games.
     */

    public ArrayList<Game> getGamesInRound(Game head, int roundNum) {
        HashSet<Game> visited = new HashSet<>();
        ArrayList<Game> games = new ArrayList<>();
        getGamesInRound(head, roundNum, visited, games);
        return games;
    }

    private void getGamesInRound(Game head, int roundNum, HashSet<Game> visited, ArrayList<Game> games) {
        if (head != null) {
            visited.add(head);
            if (head.getGameRound() == roundNum) {
                games.add(head);
                return;
            }
            if (head.getPrevGame1() != null || head.getPrevGame2() != null) {
                if (head.getPrevGame1() != null && !visited.contains(head.getPrevGame1())) {
                    getGamesInRound(head.getPrevGame1(), roundNum, visited, games);
                }
                if (head.getPrevGame2() != null && !visited.contains(head.getPrevGame2())){
                    getGamesInRound(head.getPrevGame2(), roundNum, visited, games);
                }
            }
        }
    }

}
