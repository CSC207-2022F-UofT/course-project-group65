package useCases.generalClasses.traversalStrategies;

import entities.Bracket;
import entities.Game;

import java.util.ArrayList;
import java.util.HashSet;

public class BracketMethods {
    Bracket bracket;

    public BracketMethods(Bracket bracket){
        this.bracket = bracket;
    }

    public ArrayList<Game> getGamesInRound(int roundNum) {
        HashSet<Game> visited = new HashSet<>();
        return getGamesInRound(bracket.getFinalGame(), roundNum, visited);
    }

    private ArrayList<Game> getGamesInRound(Game head, int roundNum, HashSet<Game> visited) {
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
            if (!visited.contains(head.getPrevGame1())) {
                games.addAll(getGamesInRound(head.getPrevGame1(), roundNum, visited));
            }
            if (!visited.contains(head.getPrevGame2())) {
                games.addAll(getGamesInRound(head.getPrevGame2(), roundNum, visited));
            }
            return games;
        }
    }

    public ArrayList<Game> getAllGames(){
        HashSet<Game> visited = new HashSet<>();
        getAllGames(bracket.getFinalGame(), visited);
        return new ArrayList<>(visited);
    }

    private void getAllGames(Game head, HashSet<Game> visited){
        if (head != null) {
            visited.add(head);
            if (head.getPrevGame1() != null && !visited.contains(head.getPrevGame1())) {
                getAllGames(head.getPrevGame1(), visited);
            }
            if (head.getPrevGame2() != null && !visited.contains(head.getPrevGame2())) {
                getAllGames(head.getPrevGame2(), visited);
            }
        }
    }
}




